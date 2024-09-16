package by.academy.it.task13.exception;

public class MailSenderException extends RuntimeException {
    public MailSenderException() {
    }

    public MailSenderException(String message) {
        super(message);
    }

    public MailSenderException(String message, Throwable cause) {
        super(message, cause);
    }

    public MailSenderException(Throwable cause) {
        super(cause);
    }
}
