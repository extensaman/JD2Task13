package by.academy.it.task13.util;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.ArrayList;
import java.util.List;

@Component
public class TelegramBot extends TelegramLongPollingBot {

    private static final Logger LOGGER = LogManager.getLogger(TelegramBot.class);
    private static final String TOKEN = "5527014400:AAGpoUmTsvYiIfUXis-OJl_i-BI6H-7cKEw";
    private static final String BOT_USERNAME = "CavalierHorseClubBot";

    private final List<String> listOfChatIdForBroadcasting = new ArrayList<>();




    @Override
    public void onUpdateReceived(Update update) {
        if(update.hasMessage() && update.getMessage().hasText()){
            String text = update.getMessage().getText();
            String chatId = update.getMessage().getChatId().toString();
            if("/start".equals(text)){
                sendMessage(chatId, "Enter password");
            }
            if("admin".equals(text)){
                if(listOfChatIdForBroadcasting.add(chatId)){

                    LOGGER.warn("ADDED: List size = " + listOfChatIdForBroadcasting.size());
                }
                sendMessage(chatId, "You've just subscribed to Cavalier horse club orders broadcasting");
            }
            if("/unsubscribe".equals(text)){
                listOfChatIdForBroadcasting.remove(chatId);
                sendMessage(chatId, "You've just unsubscribed from Cavalier horse club orders broadcasting");
            }
        }
    }

    public void broadcastMessage(String message){
        LOGGER.warn("List size = " + listOfChatIdForBroadcasting.size());
        listOfChatIdForBroadcasting.forEach(chatId -> {
            sendMessage(chatId, message);
        });
    }

    public void sendMessage(String chatId, String message){
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(chatId);
        sendMessage.enableMarkdown(true);
        sendMessage.setText(message);
        try {
            execute(sendMessage);
        } catch (TelegramApiException e) {
            LOGGER.warn(e);
        }
    }


    @Override
    public String getBotUsername() {
        return BOT_USERNAME;
    }

    @Override
    public String getBotToken() {
        return TOKEN;
    }
}
