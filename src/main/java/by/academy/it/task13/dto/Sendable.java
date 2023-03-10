package by.academy.it.task13.dto;

import by.academy.it.task13.entity.OrderStatus;
import by.academy.it.task13.entity.OrderType;

public interface Sendable {
    String EMPTY_STRING = "";

    String getReceiver();

    default String getSubject() {
        return EMPTY_STRING;
    }

    String getMessage();

    OrderStatus getOrderStatus();

    OrderType getOrderType();

    Long getOrderId();
}
