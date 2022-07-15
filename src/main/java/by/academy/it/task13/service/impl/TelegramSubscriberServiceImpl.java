package by.academy.it.task13.service.impl;

import by.academy.it.task13.dto.TelegramSubscriberDto;
import by.academy.it.task13.entity.TelegramSubscriber;
import by.academy.it.task13.mapper.impl.TelegramSubscriberMapper;
import by.academy.it.task13.repo.TelegramSubscriberRepository;
import by.academy.it.task13.service.TelegramSubscriberService;
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
    public static final String EMPTY_STRING = "";

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
    public boolean deactivateByChatId(String chatId) {
        return changeActivityByChatId(chatId, false);
    }

    @Override
    public void activateByMessage(Message message) {
        String chatId = message.getChatId().toString();
        findByChatId(chatId).ifPresentOrElse(subscriber -> {
                    subscriber.setActivity(true);
                    this.save(subscriber);
                },
                // orElse
                () -> {
                    this.saveTelegramSubscriberFromMessage(message);
                });
    }

    @Override
    @Transactional
    public void saveTelegramSubscriberFromMessage(Message message) {
        String subscriberName = Optional.ofNullable(message.getContact()).map(contact -> {
            String firstName = Optional.ofNullable(contact.getFirstName()).orElse(EMPTY_STRING);
            String lastName = Optional.ofNullable(contact.getLastName()).orElse(EMPTY_STRING);
            return firstName + SPACE + lastName;
        }).orElse(EMPTY_STRING);

        TelegramSubscriber subscriber = TelegramSubscriber.builder()
                .activity(true)
                .chatId(message.getChatId().toString())
                .name(subscriberName)
                .build();
        repository.save(subscriber);
    }

    @Override
    public List<String> getChatIdListWhereActivityIsTrue() {
        return repository.getChatIdListWhereActivityIsTrue();
    }

    private boolean changeActivityByChatId(String chatId, boolean activity) {
        return this.findByChatId(chatId).map(subscriberDto -> {
            subscriberDto.setActivity(activity);
            this.save(subscriberDto);
            return true;
        }).orElseGet(() -> false);
    }
}
