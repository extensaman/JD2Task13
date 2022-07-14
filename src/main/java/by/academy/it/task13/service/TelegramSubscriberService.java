package by.academy.it.task13.service;

import by.academy.it.task13.dto.TelegramSubscriberDto;
import by.academy.it.task13.entity.TelegramSubscriber;

import java.util.List;
import java.util.Optional;

public interface TelegramSubscriberService {
    Optional<TelegramSubscriberDto> findByChatId(String chatId);
    List<TelegramSubscriberDto> findAll();
    void save(TelegramSubscriberDto subscriberDto);
    void delete(TelegramSubscriberDto subscriberDto);
}
