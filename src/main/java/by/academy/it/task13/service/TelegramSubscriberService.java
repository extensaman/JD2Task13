package by.academy.it.task13.service;

import by.academy.it.task13.dto.TelegramSubscriberDto;
import org.telegram.telegrambots.meta.api.objects.Message;

import java.util.List;
import java.util.Optional;

public interface TelegramSubscriberService {
    Optional<TelegramSubscriberDto> findByChatId(String chatId);

    List<TelegramSubscriberDto> findAll();

    void save(TelegramSubscriberDto subscriberDto);

    void saveTelegramSubscriberFromMessage(Message message);

    void delete(TelegramSubscriberDto subscriberDto);

    boolean deactivateByChatId(String chatId);

    void activateByMessage(Message message);

    List<String> getChatIdListWhereActivityIsTrue();
}
