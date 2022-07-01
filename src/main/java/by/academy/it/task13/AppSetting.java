package by.academy.it.task13;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Getter
@Setter
public class AppSetting {
    @Value("${upload.path}")
    private String uploadPath;

    // MAIL
    @Value("${spring.mail.host}")
    private String mailHost;

    @Value("${spring.mail.username}")
    private String mailUsername;

    @Value("${spring.mail.password}")
    private String mailPassword;

    @Value("${spring.mail.port}")
    private int mailPort;

    @Value("${spring.mail.protocol}")
    private String mailProtocol;

    @Value("${mail.debug}")
    private String mailDebug;


    // TELEGRAM BOT
    @Value("${telegram.bot.token}")
    private String telegramBotToken;

    @Value("${telegram.bot.username}")
    private String telegramBotUsername;

    @Value("${telegram.bot.password-for-subscribe}")
    private String telegramBotPasswordForSubscribe;

    @Value("${telegram.bot.unsubscribe-command}")
    private String telegramBotUnsubscribeCommand;

}
