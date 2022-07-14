package by.academy.it.task13.util;

import by.academy.it.task13.AppSetting;
import by.academy.it.task13.dto.Sendable;
import by.academy.it.task13.dto.TelegramSubscriberDto;
import by.academy.it.task13.exception.TelegramSubscriberAnswerException;
import by.academy.it.task13.service.CertificateOrderService;
import by.academy.it.task13.service.TelegramSubscriberService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
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
    public static final String I_DON_T_UNDERSTAND_YOU = "I don't understand you.";

    private final AppSetting appSetting;
    private final TelegramSubscriberService telegramSubscriberService;
    private final CertificateOrderService certificateOrderService;

    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage() && update.getMessage().hasText()) {
            Message message = update.getMessage();
            String receivedText = message.getText();
            String chatId = message.getChatId().toString();

            if (appSetting.getTelegramBotPasswordForSubscribe().equals(receivedText)) {
                // Subscribing
                telegramSubscriberService.saveTelegramSubscriberFromMessage(message);
                sendMessage(chatId, YOU_VE_JUST_SUBSCRIBED_TO_CAVALIER_HORSE_CLUB_ORDERS_BROADCASTING);
            } else if (appSetting.getTelegramBotUnsubscribeCommand().equals(receivedText) &&
                    // Unsubscribing
                    telegramSubscriberService.deleteByChatId(chatId)) {
                sendMessage(chatId, YOU_VE_JUST_UNSUBSCRIBED_FROM_CAVALIER_HORSE_CLUB_ORDERS_BROADCASTING);
            } else if (telegramSubscriberService.findByChatId(chatId).isPresent()) {
                try {
                    SubscriberAnswer subscriberAnswer = new SubscriberAnswer(receivedText);
                    Sendable sendable;
                    switch (subscriberAnswer.getOrderType()) {
                        case CERTIFICATE:
                            sendable = certificateOrderService.findById(subscriberAnswer.getId())
                                    .orElseThrow(TelegramSubscriberAnswerException::new);
                            break;
                        case SUBSCRIPTION:
                        case PHOTO_SESSION:
                        default:
                            throw new TelegramSubscriberAnswerException();
                    }

                    // Send email

                } catch (TelegramSubscriberAnswerException e) {
                    sendMessage(chatId, I_DON_T_UNDERSTAND_YOU);
                }

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
        LOGGER.info("broadcastMessage");
        telegramSubscriberService.getChatIdList().forEach(chatId -> {
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

    @Data
    public class SubscriberAnswer {
        private static final String SPACES_REGEX = "/s";
        private static final int PARAMETER_COUNT = 2;
        private static final int ORDER_TYPE_INDEX = 0;
        private static final int ID_INDEX = 1;

        private final OrderType orderType;
        private final Long id;

        public SubscriberAnswer(String receivedText) throws TelegramSubscriberAnswerException {
            String[] answers = receivedText.split(SPACES_REGEX);
            if (answers.length < PARAMETER_COUNT) {
                throw new TelegramSubscriberAnswerException();
            }

            OrderType orderType;
            try {
                orderType = OrderType.valueOf(answers[ORDER_TYPE_INDEX]);
            } catch (IllegalArgumentException e) {
                throw new TelegramSubscriberAnswerException();
            }

            switch (orderType) {
                case CERTIFICATE:
                    this.orderType = OrderType.CERTIFICATE;
                    break;
                case SUBSCRIPTION:
                    this.orderType = OrderType.SUBSCRIPTION;
                    break;
                case PHOTO_SESSION:
                    this.orderType = OrderType.PHOTO_SESSION;
                    break;
                default:
                    throw new TelegramSubscriberAnswerException();
            }

            try {
                this.id = Long.parseLong(answers[ID_INDEX]);
            } catch (NumberFormatException e) {
                throw new TelegramSubscriberAnswerException();
            }
        }
    }


    public enum OrderType {
        CERTIFICATE,
        SUBSCRIPTION,
        PHOTO_SESSION
    }
}
