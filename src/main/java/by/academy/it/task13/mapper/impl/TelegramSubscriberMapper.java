package by.academy.it.task13.mapper.impl;

import by.academy.it.task13.dto.TelegramSubscriberDto;
import by.academy.it.task13.entity.TelegramSubscriber;
import by.academy.it.task13.mapper.Mapper;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
@RequiredArgsConstructor
public class TelegramSubscriberMapper implements Mapper<TelegramSubscriber, TelegramSubscriberDto> {
    private static final Logger logger = LogManager.getLogger(TelegramSubscriberMapper.class);
    private final ModelMapper mapper;

    @Override
    public TelegramSubscriber toEntity(TelegramSubscriberDto dto) {
        logger.info("Mapping TelegramSubscriberDto to TelegramSubscriber");
        return Objects.isNull(dto) ? null : mapper.map(dto, TelegramSubscriber.class);
    }

    @Override
    public TelegramSubscriberDto toDto(TelegramSubscriber entity) {
        logger.info("Mapping TelegramSubscriber to TelegramSubscriberDto");
        return Objects.isNull(entity) ? null : mapper.map(entity, TelegramSubscriberDto.class);
    }
}
