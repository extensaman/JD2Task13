package by.academy.it.task13.util;

import by.academy.it.task13.AppSetting;
import by.academy.it.task13.dto.OrderManipulationByTelegramLogDto;
import by.academy.it.task13.dto.Sendable;
import by.academy.it.task13.entity.OrderStatus;
import by.academy.it.task13.entity.OrderType;
import by.academy.it.task13.exception.TelegramSubscriberAnswerException;
import by.academy.it.task13.service.CertificateOrderService;
import by.academy.it.task13.service.MailSenderService;
import by.academy.it.task13.service.OrderManipulationByTelegramLogService;
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
    public static final String USER_NOTIFICATION_CANCELED = "User notification canceled.";
    public static final String CANCEL_COMMAND = "/CANCEL";
    public static final String CANCEL_TEXT = "Cancel";
    public static final String SPACE = " ";
    public static final String MESSAGE_THAT_STATUS_WAS_CHANGED_EARLIER = "Status of %1$s order #%2$s has been changed to %3$s by %4$s";
    public static final String MESSAGE_THAT_NOTIFICATION_SENT_TO_USER = "Changed status of %1$s order #%2$s. Notification letter was sent to %3$s";
    public static final String MESSAGE_THAT_NO_MORE_ORDERS_NOW = "Dear, %s, for now, I have nothing to tell you. I will notify you as soon as an order is placed.";

    private final AppSetting appSetting;
    private final TelegramSubscriberService telegramSubscriberService;
    private final CertificateOrderService certificateOrderService;
    private final MailSenderService mailSenderService;
    private final OrderManipulationByTelegramLogService orderLogService;

    private List<InlineKeyboardButton> cancelButtonRow;

    {
        InlineKeyboardButton cancelButton = new InlineKeyboardButton();
        cancelButton.setText(CANCEL_TEXT);
        cancelButton.setCallbackData(CANCEL_COMMAND);
        this.cancelButtonRow = Collections.singletonList(cancelButton);
    }

    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage() && update.getMessage().hasText()) {
            Message message = update.getMessage();
            String receivedText = message.getText();
            String chatId = message.getChatId().toString();

            if (appSetting.getTelegramBotUnsubscribeCommand().equalsIgnoreCase(receivedText)) {
                // Unsubscribing
                LOGGER.info("Unsubscribing");
                telegramSubscriberService.deactivateByChatId(chatId);
                sendTextMessage(chatId, YOU_VE_JUST_UNSUBSCRIBED_FROM_CAVALIER_HORSE_CLUB_ORDERS_BROADCASTING);
            } else if (appSetting.getTelegramBotPasswordForSubscribe().equalsIgnoreCase(receivedText)) {
                // Subscribing
                LOGGER.info("Subscribing");
                telegramSubscriberService.activateByMessage(message);
                sendTextMessage(chatId, YOU_VE_JUST_SUBSCRIBED_TO_CAVALIER_HORSE_CLUB_ORDERS_BROADCASTING);
            } else {
                telegramSubscriberService.findByChatId(chatId).ifPresentOrElse(subscriber ->
                                sendTextMessage(chatId, String.format(MESSAGE_THAT_NO_MORE_ORDERS_NOW, subscriber.getName()))
                        , () -> sendTextMessage(chatId, ENTER_THE_PASSWORD));
            }

        } else if (update.hasCallbackQuery()) {
            String chatId = update.getCallbackQuery().getMessage().getChatId().toString();
            String callbackData = update.getCallbackQuery().getData();
            LOGGER.info("Callback data is = " + callbackData);

            if (CANCEL_COMMAND.equalsIgnoreCase(callbackData)) {
                LOGGER.info("CANCEL command");
                sendTextMessage(chatId, USER_NOTIFICATION_CANCELED);
                return;
            }

            telegramSubscriberService.findByChatId(chatId).ifPresent(subscriber ->
            {
                try {
                    SubscriberAnswer subscriberAnswer = new SubscriberAnswer(callbackData);
                    LOGGER.info("SubscriberAnswer is " + subscriberAnswer.toString());
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

                    String textMessage = orderLogService.findById(subscriberAnswer.getOrderLogId()).map(log ->
                            Optional.ofNullable(log.getNewStatus()).map(orderStatus ->
                                    String.format(MESSAGE_THAT_STATUS_WAS_CHANGED_EARLIER,
                                            subscriberAnswer.getOrderType(),
                                            sendable.getOrderId(),
                                            orderStatus.name(),
                                            subscriber.getName()))
                                    .orElseGet(() -> {
                                        log.setNewStatus(subscriberAnswer.orderStatus);
                                        log.setTelegramSubscriberDto(subscriber);
                                        orderLogService.save(log);
                                        mailSenderService.sendOrderInfoByMail(sendable);
                                        return String.format(MESSAGE_THAT_NOTIFICATION_SENT_TO_USER,
                                                subscriberAnswer.getOrderType(),
                                                sendable.getOrderId(),
                                                sendable.getReceiver());
                                    })
                    ).orElseThrow(TelegramSubscriberAnswerException::new);

                    sendTextMessage(chatId, textMessage);

                } catch (TelegramSubscriberAnswerException e) {
                    sendTextMessage(chatId, I_DON_T_UNDERSTAND_YOU);
                }
            });
        }
    }

    public void broadcastOrder(Sendable sendable) {
        LOGGER.info("broadcastOrder");

        OrderManipulationByTelegramLogDto log = OrderManipulationByTelegramLogDto.builder()
                .orderType(sendable.getOrderType())
                .orderId(sendable.getOrderId())
                .oldStatus(sendable.getOrderStatus())
                .build();

        String orderLogId = orderLogService.save(log).getId().toString();

        String commonCallBackData = new StringBuilder(orderLogId)
                .append(SPACE)
                .append(sendable.getOrderType().name())
                .append(SPACE)
                .append(sendable.getOrderId().toString())
                .append(SPACE)
                .toString();
        LOGGER.info("commonCallBackData = " + commonCallBackData);
        InlineKeyboardMarkup keyboardMarkup = new InlineKeyboardMarkup();
        List<InlineKeyboardButton> row01 = Arrays.stream(OrderStatus.values()).map(status -> {
            InlineKeyboardButton button = new InlineKeyboardButton();
            button.setText(status.name());
            button.setCallbackData(commonCallBackData.concat(status.name()));
            return button;
        }).collect(Collectors.toList());

        keyboardMarkup.setKeyboard(List.of(row01, cancelButtonRow));

        telegramSubscriberService.getChatIdListWhereActivityIsTrue().forEach(chatId -> {
            sendMessage(chatId, sendable.getMessage(), Optional.of(keyboardMarkup));
        });
    }

    public void sendTextMessage(String chatId, String message) {
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
        private static final String SPACES_REGEX = "\\s";
        private static final int PARAMETER_COUNT = 4;
        private static final int ORDER_LOG_ID = 0;
        private static final int ORDER_TYPE_INDEX = 1;
        private static final int ID_INDEX = 2;
        public static final int ORDER_STATUS_INDEX = 3;

        private final Long orderLogId;
        private final OrderType orderType;
        private final Long id;
        private final OrderStatus orderStatus;

        public SubscriberAnswer(String receivedText) throws TelegramSubscriberAnswerException {
            LOGGER.info("In SubscriberAnswer.constructor receivedText = " + receivedText);
            String[] answers = receivedText.split(SPACES_REGEX);
            if (answers.length < PARAMETER_COUNT) {
                throw new TelegramSubscriberAnswerException();
            }

            try {
                this.orderLogId = Long.parseLong(answers[ORDER_LOG_ID]);
            } catch (NumberFormatException e) {
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
            switch (orderStatus) {
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
            LOGGER.info("SubscriberAnswer created");
        }
    }
}
