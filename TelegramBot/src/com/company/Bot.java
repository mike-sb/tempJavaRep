package com.company;

import com.company.model.*;
import com.company.service.Bot_Service;
import com.company.service.DB_Service;
import com.google.inject.internal.cglib.core.$ProcessArrayCallback;
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
            sendMessage = Bot_Service.getInstance().regUni(sendMessage);
            execute(sendMessage);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private void regFaculty(SendMessage sendMessage) {
        try{
            sendMessage = Bot_Service.getInstance().regFaculty(sendMessage);
            execute(sendMessage);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void regGroup(SendMessage sendMessage) {
        try{
            sendMessage = Bot_Service.getInstance().regGroup(sendMessage);
            execute(sendMessage);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    private void  newLesson(SendMessage sendMessage) {
        try{
            sendMessage = Bot_Service.getInstance().newLesson(sendMessage,day);
            execute(sendMessage);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void newTask(SendMessage sendMessage) {
        try{
            sendMessage = Bot_Service.getInstance().newTask(sendMessage);
            execute(sendMessage);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void newPersonalTask(SendMessage sendMessage) {
        try{
       sendMessage = Bot_Service.getInstance().newPersonalTask(sendMessage);
        execute(sendMessage);
    } catch (Exception e) {
            e.printStackTrace();
        }
    }



@Deprecated
    private void newSchedule(SendMessage sendMessage)
    {

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
            switch (task)
            {
                case "uni":
                    task = "";
                    sendMessage.setText(message.getText());
                    regUni(sendMessage);
                    break;
                case "group":
                    task = "";
                    sendMessage.setText(message.getText());
                    regGroup(sendMessage);
                    break;
                case "faculty":
                    task = "";
                    sendMessage.setText(message.getText());
                    regFaculty(sendMessage);
                    break;
                case "lesson":
                    task = "";

                    sendMessage.setText(message.getText());
                    newLesson(sendMessage);
                    break;
                case "day":
                    task = "lesson";
                    String reply = "Пара должна быть в формате:\n название пары, чч:мм, аудитория, чёт или нечет.";
                    sendMessage.setText(reply);
                    break;
                case "hometask":
                    task = "";
                    sendMessage.setText(message.getText());
                    newTask(sendMessage);
                    break;
                case "personal":
                    task = "";
                    sendMessage.setText(message.getText());
                    newPersonalTask(sendMessage);
                    break;
                case "getFaculty":
                    task = "";
                    sendMessage.setText(Bot_Service.getInstance().getAllFaculty(message.getText()));
                    sendMsg(sendMessage);
                    break;
                case "getGroup":
                    task = "";
                    sendMessage.setText(Bot_Service.getInstance().getAllGroups(message.getText()));
                    sendMsg(sendMessage);
                    break;
            }
        }
        else {

            if (message != null && message.hasText()) {
                String reply = "";

                switch (message.getText()) {
                    case "/help":
                        reply = "Чем могу помочь?";
                        Bot_Service.getInstance().setHelpButtons(sendMessage);
                        break;
                    case "/new_home_task":
                        reply = "Введите новое задание в форматe:\n дд.мм.гггг, название пары, само задание";
                        task = "hometask";
                        break;
                    case "/add_personal":
                        task="personal";
                        reply = "Введите новое задание в форматe:\n дд.мм.гггг, название пары, само задание";
                        break;
                    case "/start":
                        reply = "Добро пожаловать. Наш бот позволяет не забывать о расписании, " +
                                "домашних и персональных заданиях. Как это работает?\n\n" +
                                "Староста пишет боту, регистрирует Университет(если такого нет в базе), факультет, группу, и расписание." +
                                " Далее он может добавлять актуальные домашние задания. \n Для тогот чтобы пользоваться ботом, пожалуйста, зарегистрируйте университет, факультет и группу. \n\n" +
                                "Для помощи вызовите команду /help .";
                        break;
                    case "/reg_uni":
                        reply = "Введите название университета.";
                        task = "uni";
                        break;
                    case "/get_uni":
                       reply = Bot_Service.getInstance().getAllUni();
                        break;
                    case "/reg_faculty":
                        reply = "Введите название фaкультета.";
                        task = "faculty";
                        break;
                    case "/get_faculty":
                        task = "getFaculty";
                        reply =  Bot_Service.getInstance().getAllFaculty(sendMessage.getChatId());
                        break;
                    case "/reg_group":
                        task = "group";
                        reply = "Введите название группы и семестр в формате: название группы, номер семестра.";
                        break;
                    case "/get_group":
                        task = "getGroup";
                      reply =  Bot_Service.getInstance().getAllGroups(sendMessage.getChatId());
                        break;
                    case "/add_lesson":
                        task = "day";
                        reply = "Введите день недели.";
                        Bot_Service.getInstance().setDayButtons(sendMessage);
                        break;
                    case "/commands":
                        reply = "Список доступных комманд: \n /help - помощь\n/reg_uni - добавить университет(добавить себя в существующий)\n" +
                                "/get_uni - посмотреть список университетов \n/reg_faculty - добавить факультет(добавить себя в существующий)\n/get_faculty - список факультетов\n" +
                                "/setting - настройки\n/reg_group - добавить группу(добавить себя в существующую) \n/get_group - список групп \n/new_home_task - добавить новое дз(общее)\n" +
                                "/add_personal - добавить персональное дз\n/add_lesson- добавить пару для составления расписания.";
                        /*
                            keyboardFirstRow.add(new KeyboardButton("/help"));
        keyboardFirstRow.add(new KeyboardButton("/reg_uni "));
        keyboardFirstRow.add(new KeyboardButton("/get_uni"));

        keyboardSecondRow.add(new KeyboardButton("/reg_faculty"));
        keyboardSecondRow.add(new KeyboardButton("/get_faculty "));
        keyboardSecondRow.add(new KeyboardButton("/setting"));

        keyboardThirdRow.add(new KeyboardButton("/reg_group "));
        keyboardThirdRow.add(new KeyboardButton("/get_group "));
        keyboardThirdRow.add(new KeyboardButton("/new_home_task"));

        keyboardFourthRow.add(new KeyboardButton("/add_personal"));
        keyboardFourthRow.add(new KeyboardButton("/add_lesson"));
                         */
                        Bot_Service.getInstance().setCommandsButtons(sendMessage);
                        break;
                    case "/settings":
                        reply = "Настройки пока не доступны";
                        break;

                    default:  reply = "???";
                }
                //   sendMessage.setReplyToMessageId(message.getMessageId()); //make a reply
                sendMessage.setText(reply);

                sendMsg(sendMessage);
            }
        }
    }


    @Override
    public String getBotUsername() {
        return "Schedully_bot";
    }

    @Override
    public String getBotToken() {
        return "";
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
