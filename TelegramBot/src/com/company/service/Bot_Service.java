package com.company.service;

import com.company.model.*;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.ArrayList;
import java.util.List;

public class Bot_Service {
    private static Bot_Service instance;


    public static Bot_Service getInstance() {
        if (instance == null) {
            instance = new Bot_Service();
        }
        return instance;
    }

    public Bot_Service() {
    }

    public SendMessage regUni(SendMessage sendMessage) {
        try {
            //adding to the database
            User user = new User(sendMessage.getChatId());
            Uni uni = new Uni(sendMessage.getText());
            DB_Service.getInstance().Uni().addUser(uni, user);

            sendMessage.setText("Университет успещно добавлен");
            // execute(sendMessage);
            return sendMessage;
        } catch (Exception e) {
            sendMessage.setText(e.getMessage());
            return sendMessage;
        }

    }

    public SendMessage regFaculty(SendMessage sendMessage) {
        try {
            //adding to the database
            Faculty faculty = new Faculty(sendMessage.getText());
            String username = sendMessage.getChatId();
            DB_Service.getInstance().Faculty().addFaculty(faculty, username);
            sendMessage.setText("Факультет успещно добавлен");
            //  execute(sendMessage);
            return sendMessage;
        } catch (Exception e) {
            sendMessage.setText(e.getMessage());
            return sendMessage;
        }
    }

    public SendMessage regGroup(SendMessage sendMessage) {
        try {
            //adding to the database
            String str[];
            if (sendMessage.getText().contains(",")) {
                str = sendMessage.getText().split(",");
            }
            else{
                str = sendMessage.getText().split(" ");
            }
            Group group = new Group(str[0]);
            group.setSemester(Integer.parseInt(str[1]));

            String username = sendMessage.getChatId();
            DB_Service.getInstance().Group().addGroup(group, username);
            sendMessage.setText("Группа успешно добавлена");
            //    execute(sendMessage);

            return sendMessage;
        } catch (Exception e) {
            sendMessage.setText(e.getMessage());
            return sendMessage;
        }

    }

    public SendMessage newLesson(SendMessage sendMessage, String d) {
        //String time, String name, String room
        //adding to the database

        String[] str = sendMessage.getText().split(",");//name, time, room, weektype, day

        WeekType weekType = WeekType.none;

        switch (str[3]) {
            case "чёт":
                weekType = WeekType.odd;
            case "нечет":
                weekType = WeekType.even;
        }

        try {
            Day day = new Day(d);
            Lesson lesson = new Lesson(str[0], str[1], str[2], weekType, day);
            String username = sendMessage.getChatId();
            String group_name = DB_Service.getInstance().Group().getGroupName(username);

            if (group_name != "") {
                DB_Service.getInstance().Lesson().addLesson(lesson, group_name);
            }

            //    execute(sendMessage);
            sendMessage.setText("Предмет добавлен");
            return sendMessage;
        } catch (Exception e) {
            sendMessage.setText(e.getMessage());
            return sendMessage;
        }

    }

    public SendMessage newTask(SendMessage sendMessage) {
        String[] str = sendMessage.getText().split(",", 3);//date, name, task

        try {
            Task task = new Task(str[0], str[1], str[2]);

            //add connection to the group

            String username = sendMessage.getChatId();
            String group_name = DB_Service.getInstance().Group().getGroupName(username);

            if (group_name != "") {
                DB_Service.getInstance().Task().addTask(task, group_name);
            }

            //  execute(sendMessage);
            sendMessage.setText("Задание добавлено");
            return sendMessage;
        } catch (Exception e) {
            sendMessage.setText(e.getMessage());
            return sendMessage;
        }

    }

    public SendMessage newPersonalTask(SendMessage sendMessage) {
        String[] str = sendMessage.getText().split(",", 3);//date, name, task

        try {
            PersonalTask task = new PersonalTask(str[0], str[1], str[2]);

            //add connection to the group

            String username = sendMessage.getChatId();
            task.setUser_id(username);
            DB_Service.getInstance().Task().addPersonalTask(task, username);

            //execute(sendMessage);
            sendMessage.setText("Задание добавлено");
            return sendMessage;
        } catch (Exception e) {
            sendMessage.setText(e.getMessage());
            return sendMessage;
        }

    }

    public String getAllUni() {
        ArrayList<Uni> unis = DB_Service.getInstance().Uni().getAllUni();

        String str_unis = "";
        if (unis.size() > 0) {
            for (Uni u : unis) {
                str_unis += u.getName() + "\n";
            }
        } else
            return "Университетов в базе нет.";
        return str_unis;
    }

    public String getAllFaculty(String unser_name) {
        ArrayList<Faculty> faculties = DB_Service.getInstance().Faculty().getAllFaculties(unser_name);

        String str_fac = "";
        if (faculties.size() > 0) {
            for (Faculty f : faculties) {
                str_fac += f.getName() + "\n";
            }
        } else {
            return "Факультетов не найдено.";
        }
        return str_fac;
    }

    public String getAllGroups(String user_name) {
        ArrayList<Group> groups = DB_Service.getInstance().Group().getAllGroups(user_name);

        String str_group = "Группа___Семестр\n";
        if (groups.size() > 0) {
            for (Group g : groups) {
                str_group += g.getGroup_name() + " " + g.getSemester() + "\n";
            }
        } else {
            return "Групп не найдено.";
        }
        return str_group;
    }

    public String getCurrentGroupHomework(String user_name) {

        return "";
    }

    public String getCurrentPersonalHomework() {
        return "";
    }

    public void setHelpButtons(SendMessage sendMessage) {
        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
        sendMessage.setReplyMarkup(replyKeyboardMarkup);
        replyKeyboardMarkup.setSelective(true);
        replyKeyboardMarkup.setResizeKeyboard(true);
        replyKeyboardMarkup.setOneTimeKeyboard(false);

        List<KeyboardRow> keyboardRows = new ArrayList<>();
        KeyboardRow keyboardFirstRow = new KeyboardRow();

        keyboardFirstRow.add(new KeyboardButton("/commands"));
        keyboardFirstRow.add(new KeyboardButton("/settings"));

        keyboardRows.add(keyboardFirstRow);

        replyKeyboardMarkup.setKeyboard(keyboardRows);


    }


    public void setDayButtons(SendMessage sendMessage) {
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

    public void setCommandsButtons(SendMessage sendMessage) {
        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
        sendMessage.setReplyMarkup(replyKeyboardMarkup);
        replyKeyboardMarkup.setSelective(true);
        replyKeyboardMarkup.setResizeKeyboard(true);
        replyKeyboardMarkup.setOneTimeKeyboard(false);

        List<KeyboardRow> keyboardRows = new ArrayList<>();
        KeyboardRow keyboardFirstRow = new KeyboardRow();
        KeyboardRow keyboardSecondRow = new KeyboardRow();
        KeyboardRow keyboardThirdRow = new KeyboardRow();
        KeyboardRow keyboardFourthRow = new KeyboardRow();

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

        keyboardRows.add(keyboardFirstRow);
        keyboardRows.add(keyboardSecondRow);
        keyboardRows.add(keyboardThirdRow);
        keyboardRows.add(keyboardFourthRow);

        replyKeyboardMarkup.setKeyboard(keyboardRows);
    }
}
