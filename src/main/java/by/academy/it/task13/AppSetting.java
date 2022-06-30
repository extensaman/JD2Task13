package by.academy.it.task13;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Getter
@Setter
@RequiredArgsConstructor
public class AppSetting {
    @Value("${upload.path}")
    private final String uploadPath;

    @Value("${telegram.bot.token}")
    private final String telegramBotToken;

    @Value("${telegram.bot.username}")
    private final String telegramBotUsername;

    @Value("${telegram.bot.password-for-subscribe}")
    private final String telegramBotPasswordForSubscribe;

    @Value("${telegram.bot.telegram.bot.unsubscribe-command}")
    private final String telegramBotUnsubscribeCommand;

}
