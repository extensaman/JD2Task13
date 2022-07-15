package by.academy.it.task13.service.impl;

import by.academy.it.task13.dto.OrderManipulationByTelegramLogDto;
import by.academy.it.task13.entity.OrderManipulationByTelegramLog;
import by.academy.it.task13.mapper.Mapper;
import by.academy.it.task13.repo.OrderManipulationByTelegramLogRepository;
import by.academy.it.task13.service.OrderManipulationByTelegramLogService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OrderManipulationByTelegramLogServiceImpl
                implements OrderManipulationByTelegramLogService {

    private final OrderManipulationByTelegramLogRepository repository;
    private final Mapper<OrderManipulationByTelegramLog, OrderManipulationByTelegramLogDto> mapper;

    @Override
    public OrderManipulationByTelegramLog save(OrderManipulationByTelegramLogDto log) {
        return repository.save(mapper.toEntity(log));
    }

    @Override
    public Optional<OrderManipulationByTelegramLogDto> findById(Long id) {
        return repository.findById(id).map(mapper::toDto);
    }
}
