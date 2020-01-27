package com.company;

import com.company.model.Uni;
import com.company.model.User;
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

            execute(sendMessage);
            //   sendMessage(sendMessage);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }

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

        if (task.equals("uni"))
        {
            task="";
            sendMessage.setText(message.getText());
            regUni(sendMessage);
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
                        reply = "How can i help u?";
                        break;
                    case "/start":
                        reply = "Добро пожаловать. Пожалуйста зарегестрируйтесь, для того чтобы использовать наш сервис.";
                        break;
                    case "/reg_uni":
                        reply = "Введите название университета";
                        task = "uni";
                        break;
                    case "/get_uni":
                       //returning all uni in db
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
