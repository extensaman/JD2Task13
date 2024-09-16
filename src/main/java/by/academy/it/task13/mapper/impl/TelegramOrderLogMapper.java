package by.academy.it.task13.mapper.impl;

import by.academy.it.task13.dto.TelegramOrderLogDto;
import by.academy.it.task13.dto.TelegramSubscriberDto;
import by.academy.it.task13.entity.TelegramOrderLog;
import by.academy.it.task13.entity.TelegramSubscriber;
import by.academy.it.task13.mapper.Mapper;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class TelegramOrderLogMapper implements Mapper<TelegramOrderLog, TelegramOrderLogDto> {
    private static final Logger logger = LogManager.getLogger(TelegramOrderLogMapper.class);
    private final ModelMapper mapper;
    private final Mapper<TelegramSubscriber, TelegramSubscriberDto> mapperTelegramSubscriber;

    @Override
    public TelegramOrderLog toEntity(TelegramOrderLogDto dto) {
        logger.info("Mapping TelegramOrderLogMapperDto to TelegramOrderLogMapper");
        return Optional.ofNullable(dto)
                .map(logDto -> {
                    TelegramSubscriber subscriber = mapperTelegramSubscriber.toEntity(logDto.getTelegramSubscriberDto());
                    TelegramOrderLog logEntity = mapper.map(dto, TelegramOrderLog.class);
                    logEntity.setTelegramSubscriber(subscriber);
                    return logEntity;
                })
                .orElse(null);
    }

    @Override
    public TelegramOrderLogDto toDto(TelegramOrderLog entity) {
        logger.info("Mapping TelegramOrderLogMapper to TelegramOrderLogMapperDto");
        return Optional.ofNullable(entity)
                .map(logEntity -> {
                    TelegramSubscriberDto subscriberDto = mapperTelegramSubscriber.toDto(logEntity.getTelegramSubscriber());
                    TelegramOrderLogDto logDto = mapper.map(entity, TelegramOrderLogDto.class);
                    logDto.setTelegramSubscriberDto(subscriberDto);
                    return logDto;
                })
                .orElse(null);
    }
}
