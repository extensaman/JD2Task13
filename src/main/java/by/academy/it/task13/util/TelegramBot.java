package by.academy.it.task13.util;

import by.academy.it.task13.AppSetting;
import by.academy.it.task13.dto.Sendable;
import by.academy.it.task13.entity.OrderStatus;
import by.academy.it.task13.entity.OrderType;
import by.academy.it.task13.exception.TelegramSubscriberAnswerException;
import by.academy.it.task13.service.CertificateOrderService;
import by.academy.it.task13.service.MailSenderService;
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
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboard;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class TelegramBot extends TelegramLongPollingBot {
    private static final Logger LOGGER = LogManager.getLogger(TelegramBot.class);
    public static final String YOU_VE_JUST_SUBSCRIBED_TO_CAVALIER_HORSE_CLUB_ORDERS_BROADCASTING = "You've just subscribed to Cavalier horse club orders broadcasting";
    public static final String YOU_VE_JUST_UNSUBSCRIBED_FROM_CAVALIER_HORSE_CLUB_ORDERS_BROADCASTING = "You've just unsubscribed from Cavalier horse club orders broadcasting";
    public static final String ENTER_THE_PASSWORD = "Enter the password";
    public static final String I_DON_T_UNDERSTAND_YOU = "I don't understand you.";
    public static final String USER_NOTIFICATION_CANCELLED = "User notification cancelled.";
    public static final String CANCEL_COMMAND = "/CANCEL";

    private final AppSetting appSetting;
    private final TelegramSubscriberService telegramSubscriberService;
    private final CertificateOrderService certificateOrderService;
    private final MailSenderService mailSenderService;

    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage() && update.getMessage().hasText()) {
            Message message = update.getMessage();
            String receivedText = message.getText();
            String chatId = message.getChatId().toString();
            telegramSubscriberService.findByChatId(chatId).ifPresentOrElse(subscriber ->
                    // ifPresent
                    {
                        if (appSetting.getTelegramBotUnsubscribeCommand().equalsIgnoreCase(receivedText)){
                            // Unsubscribing
                            telegramSubscriberService.deleteByChatId(chatId);
                            sendTextMessage(chatId, YOU_VE_JUST_UNSUBSCRIBED_FROM_CAVALIER_HORSE_CLUB_ORDERS_BROADCASTING);
                            return;
                        } else if(CANCEL_COMMAND.equalsIgnoreCase(receivedText)){
                            sendTextMessage(chatId, USER_NOTIFICATION_CANCELLED);
                            return;
                        }

                        try {
                            SubscriberAnswer subscriberAnswer = new SubscriberAnswer(receivedText);
                            Sendable sendable;
                            switch (subscriberAnswer.getOrderType()) {
                                case CERTIFICATE:
                                    sendable = certificateOrderService.findById(subscriberAnswer.getId())
                                            .map(certificateOrderDto -> certificateOrderService.updateAndReturnCertificateOrderStatus(certificateOrderDto, subscriberAnswer.getOrderStatus()))
                                            .orElseThrow(TelegramSubscriberAnswerException::new);
                                    break;
                                case SUBSCRIPTION:
                                case PHOTO_SESSION:
                                default:
                                    throw new TelegramSubscriberAnswerException();
                            }

                            mailSenderService.sendOrderInfoByMail(sendable);

                        } catch (TelegramSubscriberAnswerException e) {
                            sendTextMessage(chatId, I_DON_T_UNDERSTAND_YOU);
                        }
                    },
                    // orElse
                    () -> {
                        if (appSetting.getTelegramBotPasswordForSubscribe().equalsIgnoreCase(receivedText)) {
                            // Subscribing
                            telegramSubscriberService.saveTelegramSubscriberFromMessage(message);
                            sendTextMessage(chatId, YOU_VE_JUST_SUBSCRIBED_TO_CAVALIER_HORSE_CLUB_ORDERS_BROADCASTING);
                        } else {
                            sendTextMessage(chatId, ENTER_THE_PASSWORD);
                        }
                    });
        }
    }

    public void broadcastOrder(Sendable sendable) {
        LOGGER.info("broadcastOrder");
        String message = sendable.getMessage();
        String commonCallBackData = sendable.getOrderType().name()
                .concat(" ")
                .concat(sendable.getOrderId().toString())
                .concat(" ");
        InlineKeyboardMarkup keyboardMarkup = new InlineKeyboardMarkup();
        List<InlineKeyboardButton> buttonList = Arrays.stream(OrderStatus.values()).map(status -> {
            InlineKeyboardButton button = new InlineKeyboardButton();
            button.setText(status.name());
            button.setCallbackData(commonCallBackData.concat(status.name()));
            return button;
        }).collect(Collectors.toList());

        Collections.singletonList()

        telegramSubscriberService.getChatIdList().forEach(chatId -> {
            sendMessage(chatId, message);
        });
    }

    public void sendTextMessage(String chatId, String message){
        sendMessage(chatId, message, Optional.empty());
    }

    public void sendMessage(String chatId, String message, Optional<ReplyKeyboard> keyboardMarkup) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(chatId);
        sendMessage.enableMarkdown(false);
        sendMessage.setText(message);
        keyboardMarkup.ifPresent(sendMessage::setReplyMarkup);

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
    private class SubscriberAnswer {
        private static final String SPACES_REGEX = "/s";
        private static final int PARAMETER_COUNT = 3;
        private static final int ORDER_TYPE_INDEX = 0;
        private static final int ID_INDEX = 1;
        public static final int ORDER_STATUS_INDEX = 2;

        private final OrderType orderType;
        private final Long id;
        private final OrderStatus orderStatus;

        public SubscriberAnswer(String receivedText) throws TelegramSubscriberAnswerException {
            String[] answers = receivedText.split(SPACES_REGEX);
            if (answers.length < PARAMETER_COUNT) {
                throw new TelegramSubscriberAnswerException();
            }

            OrderType orderType;
            try {
                orderType = OrderType.valueOf(answers[ORDER_TYPE_INDEX].toUpperCase());
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

            OrderStatus orderStatus;
            try {
                orderStatus = OrderStatus.valueOf(answers[ORDER_STATUS_INDEX].toUpperCase());
            } catch (IllegalArgumentException e) {
                throw new TelegramSubscriberAnswerException();
            }
            switch (orderStatus){
                case ACCEPTED:
                    this.orderStatus = OrderStatus.ACCEPTED;
                    break;
                case PROCESSED:
                    this.orderStatus = OrderStatus.PROCESSED;
                    break;
                case ACTIVATED:
                    this.orderStatus = OrderStatus.ACTIVATED;
                    break;
                case CLOSED:
                    this.orderStatus = OrderStatus.CLOSED;
                    break;
                default:
                    throw new TelegramSubscriberAnswerException();
            }
        }
    }
}
