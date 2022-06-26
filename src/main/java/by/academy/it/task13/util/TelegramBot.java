package by.academy.it.task13.util;

import by.academy.it.task13.entity.CertificateOrder;
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
    private static final String PASSWORD_FOR_SUBSCRIBE = "admin";
    public static final String UNSUBSCRIBE_COMMAND = "/unsubscribe";

    private final List<String> listOfChatIdForBroadcasting = new ArrayList<>();

    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage() && update.getMessage().hasText()) {
            String receivedText = update.getMessage().getText();
            String chatId = update.getMessage().getChatId().toString();
            switch (receivedText) {
                case PASSWORD_FOR_SUBSCRIBE:
                    listOfChatIdForBroadcasting.add(chatId);
                    sendMessage(chatId, "You've just subscribed to Cavalier horse club orders broadcasting");
                    break;
                case UNSUBSCRIBE_COMMAND:
                    listOfChatIdForBroadcasting.remove(chatId);
                    sendMessage(chatId, "You've just unsubscribed from Cavalier horse club orders broadcasting");
                    break;
                default:
                    sendMessage(chatId, "Enter the password");
                    break;
            }
        }
    }

    public void broadcastOrder(CertificateOrder order) {
        StringBuilder message = new StringBuilder()
                .append("ID = ")
                .append(order.getId())
                .append("\nUser name = ")
                .append(order.getUser().getUsername())
                .append("\nCertificate name = ")
                .append(order.getCertificate().getName())
                .append("\nCertificate decoration = ")
                .append(order.getCertificateDecoration().getName())
                .append("\nEvent date = ")
                .append(order.getEventDate())
                .append("\nDetails = ")
                .append(order.getDetails());
        broadcastMessage(message.toString());
    }

    public void broadcastMessage(String message) {
        LOGGER.info("List size = " + listOfChatIdForBroadcasting.size());
        listOfChatIdForBroadcasting.forEach(chatId -> {
            sendMessage(chatId, message);
        });
    }

    public void sendMessage(String chatId, String message) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(chatId);
        sendMessage.enableMarkdown(true);
        sendMessage.setText(message);
        try {
            execute(sendMessage);
            LOGGER.info("Send " + message + " to " + chatId);
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
