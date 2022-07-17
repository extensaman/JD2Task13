package by.academy.it.task13.mapper.impl;

import by.academy.it.task13.dto.OrderManipulationByTelegramLogDto;
import by.academy.it.task13.entity.OrderManipulationByTelegramLog;
import by.academy.it.task13.mapper.Mapper;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
@RequiredArgsConstructor
public class OrderManipulationByTelegramLogMapper implements Mapper<OrderManipulationByTelegramLog, OrderManipulationByTelegramLogDto> {
    private static final Logger LOGGER = LogManager.getLogger(OrderManipulationByTelegramLogMapper.class);
    private final ModelMapper mapper;

    @Override
    public OrderManipulationByTelegramLog toEntity(OrderManipulationByTelegramLogDto dto) {
        LOGGER.info("Mapping OrderManipulationByTelegramLogDto to OrderManipulationByTelegramLog");
        return Objects.isNull(dto) ? null : mapper.map(dto, OrderManipulationByTelegramLog.class);
    }

    @Override
    public OrderManipulationByTelegramLogDto toDto(OrderManipulationByTelegramLog entity) {
        LOGGER.info("Mapping OrderManipulationByTelegramLog to OrderManipulationByTelegramLogDto");
        return Objects.isNull(entity) ? null : mapper.map(entity, OrderManipulationByTelegramLogDto.class);
    }
}
