package com.company;

import com.company.model.*;
import com.company.service.DB_Service;
import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.meta.exceptions.TelegramApiRequestException;

import java.util.ArrayList;
import java.util.List;

public class Bot extends TelegramLongPollingBot {

    private String task="";
    private String day= "";
    //bots response
    private void sendMsg(SendMessage sendMessage) {

        try{
            execute(sendMessage);
            //   sendMessage(sendMessage);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }

    }
    private void regUni(SendMessage sendMessage) {
        try{
            //adding to the database
            User user = new User(sendMessage.getChatId());
            Uni uni = new Uni(sendMessage.getText());
            DB_Service.getInstance().getUni().addUser(uni,user);
            execute(sendMessage);
            //   sendMessage(sendMessage);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }

    }
    private void regFaculty(SendMessage sendMessage) {
        try{
            //adding to the database
            Faculty faculty = new Faculty(sendMessage.getText());
            String username = sendMessage.getChatId();
            DB_Service.getInstance().getFaculty().addFaculty(faculty,username);
            execute(sendMessage);
            //   sendMessage(sendMessage);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    private void regGroup(SendMessage sendMessage) {
        try{
            //adding to the database
            Group group = new Group(sendMessage.getText());
            String username = sendMessage.getChatId();
            DB_Service.getInstance().getGroup().addGroup(group,username);
            execute(sendMessage);
            //   sendMessage(sendMessage);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }

    }
    private String  newLesson(SendMessage sendMessage) {
            //String time, String name, String room
            //adding to the database
        String[]str = sendMessage.getText().split(",");//name, time, room, weektype, day

        WeekType weekType= WeekType.none;

        switch (str[3])
        {
            case "чёт":
                weekType = WeekType.odd;
            case "нечет":
                weekType = WeekType.even;
        }

        try {
            Day day = new Day(this.day);
            Lesson lesson = new Lesson(str[0],str[1],str[2],weekType,day);
            String username = sendMessage.getChatId();
            String group_name = DB_Service.getInstance().getGroup().getGroupName(username);

            if (group_name!="") {
                DB_Service.getInstance().getLesson().addLesson(lesson, group_name);
            }
        execute(sendMessage);
            return "Предмет добавлен";
        } catch (Exception e) {
            return e.getMessage();
        }

    }

    private String newTask(SendMessage sendMessage) {
        String[]str = sendMessage.getText().split(",",3);//date, name, task

        try {
            Task task = new Task(str[0],str[1],str[2]);

            //add connection to the group

            String username = sendMessage.getChatId();
            String group_name = DB_Service.getInstance().getGroup().getGroupName(username);

            if (group_name!="") {
                DB_Service.getInstance().getTask().addTask(task,group_name);
            }
            execute(sendMessage);
            return "Задание добавлено";
        } catch (Exception e) {
            return e.getMessage();
        }

    }



    private void setDayButtons(SendMessage sendMessage)
    {
        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
        sendMessage.setReplyMarkup(replyKeyboardMarkup);
        replyKeyboardMarkup.setSelective(true);
        replyKeyboardMarkup.setResizeKeyboard(true);
        replyKeyboardMarkup.setOneTimeKeyboard(false);

        List<KeyboardRow> keyboardRows = new ArrayList<>();
        KeyboardRow keyboardFirstRow = new KeyboardRow();
        KeyboardRow keyboardSecondRow = new KeyboardRow();

        keyboardFirstRow.add(new KeyboardButton("Понедельник"));
        keyboardFirstRow.add(new KeyboardButton("Вторник"));
        keyboardFirstRow.add(new KeyboardButton("Среда"));

        keyboardSecondRow.add(new KeyboardButton("Четверг"));
        keyboardSecondRow.add(new KeyboardButton("Пятница"));
        keyboardSecondRow.add(new KeyboardButton("Суббота"));

        keyboardRows.add(keyboardFirstRow);
        keyboardRows.add(keyboardSecondRow);

        replyKeyboardMarkup.setKeyboard(keyboardRows);

    }
@Deprecated
    private void newSchedule(SendMessage sendMessage)
    {
       //      try{
//            //adding to the database
//            WeekType weekType= WeekType.none;
//
//            switch (sendMessage.getText())
//            {
//                case "чёт":
//                    weekType = WeekType.odd;
//                case "нечет":
//                    weekType = WeekType.even;
//            }
//
//            Schedule schedule = new Schedule(weekType);
//            String username = sendMessage.getChatId();
//            String group_name = DB_Service.getInstance().getGroup().getGroupName(username);
//            DB_Service.getInstance().getSchedule().addSchedule(schedule,group_name);
//
//            execute(sendMessage);
//        } catch (TelegramApiException e) {
//            e.printStackTrace();
//        }
    }


    //sending messages through LongPool
    //after i want to include Web-Hooks
    @Override
    public void onUpdateReceived(Update update) {
        Message message = update.getMessage();
        SendMessage sendMessage = new SendMessage();
        sendMessage.enableMarkdown(true);
        sendMessage.enableHtml(true);
        sendMessage.setChatId(message.getChatId().toString());

        if(!task.equals("")) {
            if (task.equals("uni")) {
                task = "";
                sendMessage.setText(message.getText());
                regUni(sendMessage);
            }
            if (task.equals("day")) {
                task = "lesson";
                String reply = "Пара должна быть в формате:\n название пары, чч:мм, аудитория, чёт или нечет.";
                sendMessage.setText(reply);
            }
            if (task.equals("lesson")) {
                task = "";

                sendMessage.setText(message.getText());
                newLesson(sendMessage);
            }
            if (task.equals("hometask")) {
                task = "";

                sendMessage.setText(message.getText());
                newTask(sendMessage);
            }
        }
        else {

            if (message != null && message.hasText()) {
                String reply = "";

                switch (message.getText()) {
                    case "/help":
                        reply = "How can i help u?";
                        setHelpButtons(sendMessage);
                        break;
                    case "/new_home_task":
                        reply = "Введите новое задание в форматe:\n дд.мм.гггг, названиепары, само задание";
                        task = "hometask";
                        break;
                    case "/start":
                        reply = "Добро пожаловать. Пожалуйста зарегестрируйтесь, для того чтобы использовать наш сервис.";
                        break;
                    case "/reg_uni":
                        reply = "Введите название университета.";
                        task = "uni";
                        break;
                    case "/get_uni":
                       //returning all uni in db
                        break;
                    case "/reg_faculty":
                        reply = "Введите название фвкультета.";
                        task = "uni";
                        break;
                    case "/add_lesson":
                        task = "day";
                        reply = "Введите день недели.";
                        setDayButtons(sendMessage);
                        break;

                    default:
                }

                //   sendMessage.setReplyToMessageId(message.getMessageId());
                sendMessage.setText(reply);

                sendMsg(sendMessage);
            }
        }
    }


    public void setHelpButtons(SendMessage sendMessage)  {
        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
        sendMessage.setReplyMarkup(replyKeyboardMarkup);
        replyKeyboardMarkup.setSelective(true);
        replyKeyboardMarkup.setResizeKeyboard(true);
        replyKeyboardMarkup.setOneTimeKeyboard(false);

        List<KeyboardRow> keyboardRows = new ArrayList<>();
        KeyboardRow keyboardFirstRow = new KeyboardRow();

        keyboardFirstRow.add(new KeyboardButton("/help"));
        keyboardFirstRow.add(new KeyboardButton("/settings"));

        keyboardRows.add(keyboardFirstRow);

        replyKeyboardMarkup.setKeyboard(keyboardRows);


    }

    @Override
    public String getBotUsername() {
        return "Schedully_bot";
    }

    @Override
    public String getBotToken() {
        return "1050325278:AAF4c_Woln5PnWLKJZm3KbiDwBjBeAyVQmM";
    }

    //    @Override
//    public void onUpdatesReceived(List<Update> updates) {
//
//    }

//    @Override
//    public void onClosing() {
//
//    }





}
