package by.academy.it.task13.service.impl;

import by.academy.it.task13.dto.Sendable;
import by.academy.it.task13.service.MailSenderService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MailSenderServiceImpl implements MailSenderService {

    public static final String ORDER_IS_ACCEPTED = "Order is accepted";
    public static final String THANK_YOU_FOR_CHOOSING_US = "\nThank you for choosing us!";
    public static final String YOUR_ORDER_IS_SUCCESSFULLY_ACCEPTED = "Your order is successfully accepted.\n";

    private final JavaMailSender sender;

    @Value("${spring.mail.username}")
    private String emailFrom;

    @Override
    public void send(String emailTo, String subject, String message){
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setFrom(emailFrom);
        mailMessage.setTo(emailTo);
        mailMessage.setSubject(subject);
        mailMessage.setText(message);
        sender.send(mailMessage);
    }

    @Override
    public void sendOrderAcceptanceMail(Sendable sendable) {
        String message = new StringBuilder()
                .append(YOUR_ORDER_IS_SUCCESSFULLY_ACCEPTED)
                .append(sendable.getMessage())
                .append(THANK_YOU_FOR_CHOOSING_US)
                .toString();
        send(sendable.getReceiver(), ORDER_IS_ACCEPTED, message);
    }
}
