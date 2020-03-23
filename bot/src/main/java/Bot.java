import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.TelegramBotsApi;
import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Message;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.exceptions.TelegramApiException;
import org.telegram.telegrambots.exceptions.TelegramApiRequestException;

import java.util.ArrayList;
import java.util.List;

public class Bot extends TelegramLongPollingBot {


    public static void main(String[] args) {
        ApiContextInitializer.init();
        TelegramBotsApi telegramBotsApi = new TelegramBotsApi();
        try {
            telegramBotsApi.registerBot(new Bot());
        }catch (TelegramApiException e)
        {
            e.printStackTrace();
        }
    }

    //bots response
    private void sendMsg(Message message, String response) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.enableMarkdown(true);
        sendMessage.enableHtml(true);
        sendMessage.setChatId(message.getChatId().toString());
        sendMessage.setReplyToMessageId(message.getMessageId());
        sendMessage.setText(response);
        try{
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
        if(message!=null && message.hasText())
        {
            switch (message.getText())
            {
                case "/help":
                    sendMsg(message, "How can i help u?");
                    break;
                case "/settings":
                    sendMsg(message, "What would we change?");
                    break;
                default:
            }
        }

    }



    @Override
    public String getBotUsername() {
        return "SportfieldCom_Bot";
    }

    @Override
    public String getBotToken() {
        return "905276514:AAG-zRgNAKIhF9axvONXTRpGfmKCsm_90ls";
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