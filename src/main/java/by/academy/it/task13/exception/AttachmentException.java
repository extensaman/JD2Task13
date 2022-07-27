package by.academy.it.task13.exception;

public class AttachmentException extends RuntimeException{
    public AttachmentException() {
    }

    public AttachmentException(String message) {
        super(message);
    }

    public AttachmentException(String message, Throwable cause) {
        super(message, cause);
    }

    public AttachmentException(Throwable cause) {
        super(cause);
    }
}
