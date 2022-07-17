package by.academy.it.task13.service.impl;

import by.academy.it.task13.dto.OrderManipulationByTelegramLogDto;
import by.academy.it.task13.dto.TelegramSubscriberDto;
import by.academy.it.task13.entity.OrderManipulationByTelegramLog;
import by.academy.it.task13.entity.TelegramSubscriber;
import by.academy.it.task13.mapper.Mapper;
import by.academy.it.task13.repo.OrderManipulationByTelegramLogRepository;
import by.academy.it.task13.service.OrderManipulationByTelegramLogService;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OrderManipulationByTelegramLogServiceImpl
                implements OrderManipulationByTelegramLogService {
    private static final Logger LOGGER = LogManager.getLogger(OrderManipulationByTelegramLogServiceImpl.class);

    private final OrderManipulationByTelegramLogRepository repository;
    private final Mapper<OrderManipulationByTelegramLog, OrderManipulationByTelegramLogDto> mapper;
    private final Mapper<TelegramSubscriber, TelegramSubscriberDto> mapperTelegramSubscriber;

    @Override
    public OrderManipulationByTelegramLog save(OrderManipulationByTelegramLogDto logDto) {
        TelegramSubscriber subscriber = mapperTelegramSubscriber.toEntity(logDto.getTelegramSubscriberDto());
        OrderManipulationByTelegramLog logEntity = mapper.toEntity(logDto);
        logEntity.setTelegramSubscriber(subscriber);
        return repository.save(logEntity);
    }

    @Override
    public Optional<OrderManipulationByTelegramLogDto> findById(Long id) {
        return repository.findById(id).map(log -> {
            TelegramSubscriberDto subscriberDto = mapperTelegramSubscriber.toDto(log.getTelegramSubscriber());
            OrderManipulationByTelegramLogDto logDto = mapper.toDto(log);
            logDto.setTelegramSubscriberDto(subscriberDto);
            return logDto;
        });
    }
}
