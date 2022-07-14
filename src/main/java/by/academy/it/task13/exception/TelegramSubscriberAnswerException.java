package by.academy.it.task13.exception;

public class TelegramSubscriberAnswerException extends Exception{
    public TelegramSubscriberAnswerException() {
    }

    public TelegramSubscriberAnswerException(String message) {
        super(message);
    }

    public TelegramSubscriberAnswerException(String message, Throwable cause) {
        super(message, cause);
    }

    public TelegramSubscriberAnswerException(Throwable cause) {
        super(cause);
    }
}
