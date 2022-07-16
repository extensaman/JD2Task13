package by.academy.it.task13.service;

import by.academy.it.task13.dto.Sendable;
import by.academy.it.task13.exception.MailSenderException;

public interface MailSenderService {
    void send(String emailTo, String subject, String message);

    void sendOrderInfoByMail(Sendable sendable);
}
