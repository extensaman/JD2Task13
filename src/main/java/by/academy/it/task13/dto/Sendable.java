package by.academy.it.task13.dto;

public interface Sendable {
    String EMPTY_STRING = "";

    String getReceiver();

    default String getSubject() {
        return EMPTY_STRING;
    }

    String getMessage();

    String getOrderStatusString();
}
