package by.academy.it.task13.service;

import by.academy.it.task13.dto.OrderManipulationByTelegramLogDto;
import by.academy.it.task13.entity.OrderManipulationByTelegramLog;

import java.util.Optional;

public interface OrderManipulationByTelegramLogService {
    OrderManipulationByTelegramLog save(OrderManipulationByTelegramLogDto log);

    Optional<OrderManipulationByTelegramLogDto> findById(Long id);
}
