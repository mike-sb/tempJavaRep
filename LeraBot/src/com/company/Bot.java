package com.company;

import com.sun.jdi.event.StepEvent;
import org.glassfish.jersey.server.model.Routed;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Bot extends TelegramLongPollingBot {


    private void sendPic(String type, SendMessage sm)
    {
        SendPhoto msg_img = null;
        try {
            Random rand = new Random();
            int bound = 15;
            int rand_pic =  rand.nextInt(bound);
            if(type.equals("greet"))
            {
                msg_img = new SendPhoto().setPhoto("random)", new FileInputStream(new File("src_img/hello.png")));
                msg_img.setCaption("Привееет зайка, удачного тебе дня. :3 Создатель этого бота передаёт привет!");
            }
            else if(type.equals("help"))
            {
                msg_img = new SendPhoto().setPhoto("random)", new FileInputStream(new File("src_img/help.png")));
                msg_img.setCaption("Чем могу помочь? <3");
            }
            else {
                msg_img = new SendPhoto().setPhoto("random)", new FileInputStream(new File("src_img/" + (rand_pic + 1) + ".png")));
                msg_img.setCaption("Держи :3");
            }

            msg_img.setChatId(sm.getChatId());
            execute(msg_img);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onUpdateReceived(Update update) {
        Message message = update.getMessage();
        SendMessage sendMessage = new SendMessage();
        sendMessage.enableMarkdown(true);
        sendMessage.enableHtml(true);
        String type = "";
        sendMessage.setChatId(message.getChatId().toString());

        if (message != null && message.hasText()) {
            String reply = "";

            System.out.println("texted");

            switch (message.getText()) {
                case "/help":
//                    reply = "Чем могу помочь?";
                    setHelpButtons(sendMessage);
                    type = "help";
//                    sendMessage.setText(reply);
//                    sendMsg(sendMessage);
                    sendPic(type,sendMessage);
                    break;
                case "/start":
                    reply = "Чтобы посмотреть что умеет этот бот жмакни на /help. Ты увидишь списочек доступных команд). ";
                    type = "greet";
                    sendMessage.setText(reply);
                    sendMsg(sendMessage);
                    sendPic(type,sendMessage);

                    break;
                case "/rand":
                    type = "rand";
                    sendPic(type,sendMessage);
                    break;

                default:
                    reply = "Такой команды я не знаю(";
                    sendMessage.setReplyToMessageId(message.getMessageId()); //make a reply
                    sendMessage.setText(reply);

                    sendMsg(sendMessage);
                    break;
            }



        }
    }

    private void setHelpButtons(SendMessage sendMessage) {
        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
        sendMessage.setReplyMarkup(replyKeyboardMarkup);
        replyKeyboardMarkup.setSelective(true);
        replyKeyboardMarkup.setResizeKeyboard(true);
        replyKeyboardMarkup.setOneTimeKeyboard(false);

        List<KeyboardRow> keyboardRows = new ArrayList<>();
        KeyboardRow keyboardFirstRow = new KeyboardRow();

        keyboardFirstRow.add(new KeyboardButton("/rand"));
        keyboardFirstRow.add(new KeyboardButton("/help"));


        keyboardRows.add(keyboardFirstRow);

        replyKeyboardMarkup.setKeyboard(keyboardRows);
    }

    private void sendMsg(SendMessage sendMessage) {
        try{
            execute(sendMessage);
              // sendMessage(sendMessage);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    @Override
        public String getBotUsername () {
            return "Lera_kittyBot";
        }

        @Override
        public String getBotToken () {
            return "952130869:AAF7ESQXcsP--x5O49JMguRbRQvHEzNJej0";
        }
        
    }

   
