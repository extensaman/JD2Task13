package by.academy.it.task13.service;

public interface MailSenderService {
    void send(String emailTo, String subject, String message);
}
