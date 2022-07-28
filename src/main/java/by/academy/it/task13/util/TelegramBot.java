package by.academy.it.task13.util;

import by.academy.it.task13.AppSetting;
import by.academy.it.task13.dto.Sendable;
import by.academy.it.task13.dto.TelegramOrderLogDto;
import by.academy.it.task13.dto.TelegramSubscriberDto;
import by.academy.it.task13.entity.OrderStatus;
import by.academy.it.task13.entity.OrderType;
import by.academy.it.task13.exception.TelegramSubscriberAnswerException;
import by.academy.it.task13.service.CertificateOrderService;
import by.academy.it.task13.service.MailSenderService;
import by.academy.it.task13.service.TelegramOrderLogService;
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
    private static final Logger logger = LogManager.getLogger(TelegramBot.class);
    private static final String YOU_VE_JUST_SUBSCRIBED_TO_CAVALIER_HORSE_CLUB_ORDERS_BROADCASTING = "You've just subscribed to Cavalier horse club orders broadcasting";
    private static final String YOU_VE_JUST_UNSUBSCRIBED_FROM_CAVALIER_HORSE_CLUB_ORDERS_BROADCASTING = "You've just unsubscribed from Cavalier horse club orders broadcasting";
    private static final String ENTER_THE_PASSWORD = "Enter the password";
    private static final String I_DON_T_UNDERSTAND_YOU = "I don't understand you.";
    private static final String SPACE = " ";
    private static final String MESSAGE_THAT_STATUS_WAS_CHANGED_EARLIER = "Status of %1$s order #%2$s has been changed to %3$s by %4$s";
    private static final String MESSAGE_THAT_NOTIFICATION_SENT_TO_USER = "Changed status of %1$s order #%2$s to %3$s. Notification letter was sent to %4$s";
    private static final String MESSAGE_THAT_NO_MORE_ORDERS_NOW = "Dear, %s, for now, I have nothing to tell you. I will notify you as soon as an order is placed.";

    private final AppSetting appSetting;
    private final TelegramSubscriberService telegramSubscriberService;
    private final CertificateOrderService certificateOrderService;
    private final MailSenderService mailSenderService;
    private final TelegramOrderLogService orderLogService;

    @Override
    public void onUpdateReceived(Update update) {
        // Text message section
        if (update.hasMessage() && update.getMessage().hasText()) {
            Message message = update.getMessage();
            String receivedText = message.getText();
            String chatId = message.getChatId().toString();
            Optional<TelegramSubscriberDto> subscriberDto = telegramSubscriberService.findByChatId(chatId);

            String textMessage;
            if (appSetting.getTelegramBotUnsubscribeCommand().equalsIgnoreCase(receivedText)) {
                // Unsubscribing
                textMessage = subscriberDto.map(subscriber -> {
                    logger.info("Unsubscribing");
                    telegramSubscriberService.deactivateByChatId(chatId);
                    return YOU_VE_JUST_UNSUBSCRIBED_FROM_CAVALIER_HORSE_CLUB_ORDERS_BROADCASTING;
                }).orElse(ENTER_THE_PASSWORD);

            } else {
                textMessage = subscriberDto.filter(TelegramSubscriberDto::isActivity)
                        .map(subscriber -> String.format(MESSAGE_THAT_NO_MORE_ORDERS_NOW, subscriber.getName()))
                        .orElseGet(() -> {
                            if (appSetting.getTelegramBotPasswordForSubscribe().equalsIgnoreCase(receivedText)) {
                                // Subscribing
                                logger.info("Subscribing");
                                telegramSubscriberService.activateByMessage(message);
                                return YOU_VE_JUST_SUBSCRIBED_TO_CAVALIER_HORSE_CLUB_ORDERS_BROADCASTING;
                            } else {
                                return ENTER_THE_PASSWORD;
                            }
                        });
            }
            sendTextMessage(chatId, textMessage);

            // CallbackQuery (buttons pressed) section
        } else if (update.hasCallbackQuery()) {
            String chatId = update.getCallbackQuery().getMessage().getChatId().toString();
            String callbackData = update.getCallbackQuery().getData();
            logger.info("Callback data is = " + callbackData);

            telegramSubscriberService.findByChatId(chatId).ifPresent(subscriber ->
            {
                try {
                    SubscriberAnswer subscriberAnswer = new SubscriberAnswer(callbackData);
                    logger.info("SubscriberAnswer is " + subscriberAnswer.toString());
                    Sendable sendable;
                    switch (subscriberAnswer.getOrderType()) {
                        case CERTIFICATE:
                            sendable = certificateOrderService.findByIdFetchLazy(subscriberAnswer.getId())
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
                                            log.getTelegramSubscriberDto().getName()))
                                    .orElseGet(() -> {
                                        log.setNewStatus(subscriberAnswer.orderStatus);
                                        log.setTelegramSubscriberDto(subscriber);
                                        orderLogService.save(log);
                                        mailSenderService.sendOrderInfoByMail(sendable);
                                        return String.format(MESSAGE_THAT_NOTIFICATION_SENT_TO_USER,
                                                subscriberAnswer.getOrderType(),
                                                sendable.getOrderId(),
                                                subscriberAnswer.orderStatus,
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

    // broadcast info about order to all subscribers
    public void broadcastOrder(Sendable sendable) {
        logger.info("broadcastOrder");

        TelegramOrderLogDto log = TelegramOrderLogDto.builder()
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
        logger.info("commonCallBackData = " + commonCallBackData);
        InlineKeyboardMarkup keyboardMarkup = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> keyboard = Arrays.stream(OrderStatus.values()).map(status -> {
            InlineKeyboardButton button = new InlineKeyboardButton();
            button.setText(status.name());
            button.setCallbackData(commonCallBackData.concat(status.name()));
            return Collections.singletonList(button);
        }).collect(Collectors.toList());

        keyboardMarkup.setKeyboard(keyboard);

        telegramSubscriberService.getChatIdListWhereActivityIsTrue().forEach(chatId -> {
            sendMessage(chatId, sendable.getMessage(), Optional.of(keyboardMarkup));
        });
    }

    // broadcast simple text message to all subscribers
    public void broadcastTextMessage(String textMessage) {
        telegramSubscriberService.getChatIdListWhereActivityIsTrue().forEach(chatId -> {
            sendTextMessage(chatId, textMessage);
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
            logger.info("Send " + message + " to " + chatId);
        } catch (TelegramApiException e) {
            logger.warn(e);
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
            logger.info("In SubscriberAnswer.constructor receivedText = " + receivedText);
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
            logger.info("SubscriberAnswer created");
        }
    }
}
