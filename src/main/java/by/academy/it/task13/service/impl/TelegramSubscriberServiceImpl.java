package by.academy.it.task13.service.impl;

import by.academy.it.task13.dto.Sendable;
import by.academy.it.task13.dto.TelegramSubscriberDto;
import by.academy.it.task13.entity.TelegramSubscriber;
import by.academy.it.task13.mapper.impl.TelegramSubscriberMapper;
import by.academy.it.task13.repo.TelegramSubscriberRepository;
import by.academy.it.task13.service.TelegramSubscriberService;
import by.academy.it.task13.util.TelegramBot;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.telegram.telegrambots.meta.api.objects.Message;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TelegramSubscriberServiceImpl implements TelegramSubscriberService {
    private static final Logger LOGGER = LogManager.getLogger(TelegramSubscriberServiceImpl.class);
    public static final char SPACE = ' ';

    private final TelegramSubscriberRepository repository;
    private final TelegramSubscriberMapper mapper;

    @Override
    public Optional<TelegramSubscriberDto> findByChatId(String chatId) {
        LOGGER.info("findByChatId");
        return repository.findByChatId(chatId).map(mapper::toDto);
    }

    @Override
    public List<TelegramSubscriberDto> findAll() {
        LOGGER.info("findAll");
        List<TelegramSubscriberDto> list = new ArrayList<>();
        repository.findAll().forEach(subscriber ->
                list.add(mapper.toDto(subscriber)));
        return list;
    }

    @Override
    @Transactional
    public void save(TelegramSubscriberDto subscriberDto) {
        repository.save(mapper.toEntity(subscriberDto));
    }

    @Override
    @Transactional
    public void delete(TelegramSubscriberDto subscriberDto) {
        repository.delete(mapper.toEntity(subscriberDto));
    }

    @Override
    public boolean deleteByChatId(String chatId) {
        return this.findByChatId(chatId).map(subscriberDto -> {
            this.delete(subscriberDto);
            return true;
        }).orElseGet(() -> false);
    }

    @Override
    public void setRequestSentToTrueAndUpdate(TelegramSubscriberDto subscriberDto) {
        subscriberDto.setRequestSent(true);
        this.save(subscriberDto);
    }

    @Override
    @Transactional
    public void saveTelegramSubscriberFromMessage(Message message) {
        TelegramSubscriber subscriber = TelegramSubscriber.builder()
                .activity(true)
                .chatId(message.getChatId().toString())
                .name(message.getContact().getFirstName() + SPACE + message.getContact().getLastName())
                .build();
        repository.save(subscriber);
    }

    @Override
    public List<String> getChatIdList() {
        return repository.getChatIdList();
    }
}
