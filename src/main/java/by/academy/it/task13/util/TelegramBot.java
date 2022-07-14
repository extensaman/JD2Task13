package by.academy.it.task13.util;

import by.academy.it.task13.AppSetting;
import by.academy.it.task13.dto.Sendable;
import by.academy.it.task13.dto.TelegramSubscriberDto;
import by.academy.it.task13.entity.CertificateOrder;
import by.academy.it.task13.service.TelegramSubscriberService;
import lombok.RequiredArgsConstructor;
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
@RequiredArgsConstructor
public class TelegramBot extends TelegramLongPollingBot {
    private static final Logger LOGGER = LogManager.getLogger(TelegramBot.class);
    public static final String YOU_VE_JUST_SUBSCRIBED_TO_CAVALIER_HORSE_CLUB_ORDERS_BROADCASTING = "You've just subscribed to Cavalier horse club orders broadcasting";
    public static final String YOU_VE_JUST_UNSUBSCRIBED_FROM_CAVALIER_HORSE_CLUB_ORDERS_BROADCASTING = "You've just unsubscribed from Cavalier horse club orders broadcasting";
    public static final String ENTER_THE_PASSWORD = "Enter the password";

    private final AppSetting appSetting;
    private final TelegramSubscriberService telegramSubscriberService;
    private final List<String> listOfChatIdForBroadcasting = new ArrayList<>();

    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage() && update.getMessage().hasText()) {
            String receivedText = update.getMessage().getText();
            String chatId = update.getMessage().getChatId().toString();
            update.getMessage().getContact().getFirstName();

            if(receivedText.equals(appSetting.getTelegramBotPasswordForSubscribe())){
                listOfChatIdForBroadcasting.add(chatId);
                sendMessage(chatId, YOU_VE_JUST_SUBSCRIBED_TO_CAVALIER_HORSE_CLUB_ORDERS_BROADCASTING);
            } else if(receivedText.equals(appSetting.getTelegramBotUnsubscribeCommand())){
                listOfChatIdForBroadcasting.remove(chatId);
                sendMessage(chatId, YOU_VE_JUST_UNSUBSCRIBED_FROM_CAVALIER_HORSE_CLUB_ORDERS_BROADCASTING);
            } else {
                sendMessage(chatId, ENTER_THE_PASSWORD);
            }
        }
    }

    public void broadcastOrder(Sendable sendable) {
        LOGGER.info("broadcastOrder");
        broadcastMessage(sendable.getMessage());
    }

    public void broadcastMessage(String message) {
        LOGGER.info("List size = " + listOfChatIdForBroadcasting.size());
        List<TelegramSubscriberDto> all = telegramSubscriberService.findAll();
        all.forEach(subscriber -> {
            sendMessage(subscriber.getChatId(), message);
            //telegramSubscriberService.save(subscriber.setRequestSent(true));
        });
        listOfChatIdForBroadcasting.forEach(chatId -> {
            sendMessage(chatId, message);
        });
    }

    public void sendMessage(String chatId, String message) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(chatId);
        sendMessage.enableMarkdown(false);
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
        return appSetting.getTelegramBotUsername();
    }

    @Override
    public String getBotToken() {
        return appSetting.getTelegramBotToken();
    }
}
