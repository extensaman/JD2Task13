package by.academy.it.task13.util;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

public class TelegramBot extends TelegramLongPollingBot {

    public static final String TOKEN = "5527014400:AAGpoUmTsvYiIfUXis-OJl_i-BI6H-7cKEw";
    public static final String BOT_USERNAME = "CavalierHorseClubBot";

    @Override
    public String getBotToken() {
        return TOKEN;
    }

    @Override
    public void onUpdateReceived(Update update) {
        String text = update.getMessage().getText();
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(update.getMessage().getChatId().toString());
        sendMessage.enableMarkdown(true);
        sendMessage.setText("Answer " + text);
        sendMessage(sendMessage);
    }

    @Override
    public String getBotUsername() {
        return BOT_USERNAME;
    }
}
