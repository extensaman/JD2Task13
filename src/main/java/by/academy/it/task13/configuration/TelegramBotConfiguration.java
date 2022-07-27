package by.academy.it.task13.configuration;

import by.academy.it.task13.util.TelegramBot;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.meta.generics.BotSession;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

import java.util.Optional;

@Configuration
public class TelegramBotConfiguration {
    private static final Logger logger = LogManager.getLogger(TelegramBotConfiguration.class);

    @Bean
    public Optional<BotSession> getBotSession(TelegramBot telegramBot) {
        BotSession botSession = null;
        try {
            TelegramBotsApi telegramBotsApi = new TelegramBotsApi(DefaultBotSession.class);
            logger.info("TelegramBotApi created");
            botSession = telegramBotsApi.registerBot(telegramBot);
            logger.info("TelegramBot registered");
        } catch (TelegramApiException e) {
            logger.warn("Telegram bot isn't registered");
        }
        return Optional.ofNullable(botSession);
    }
}
