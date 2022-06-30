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

    @Value("${telegram.bot.token}")
    private String telegramBotToken;

    @Value("${telegram.bot.username}")
    private String telegramBotUsername;

    @Value("${telegram.bot.password-for-subscribe}")
    private String telegramBotPasswordForSubscribe;

    @Value("${telegram.bot.unsubscribe-command}")
    private String telegramBotUnsubscribeCommand;

}
