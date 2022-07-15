package by.academy.it.task13.mapper.impl;

import by.academy.it.task13.dto.OrderManipulationByTelegramLogDto;
import by.academy.it.task13.entity.OrderManipulationByTelegramLog;
import by.academy.it.task13.mapper.Mapper;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
@RequiredArgsConstructor
public class OrderManipulationByTelegramLogMapper implements Mapper<OrderManipulationByTelegramLog, OrderManipulationByTelegramLogDto> {
    private final ModelMapper mapper;

    @Override
    public OrderManipulationByTelegramLog toEntity(OrderManipulationByTelegramLogDto dto) {
        return Objects.isNull(dto) ? null : mapper.map(dto, OrderManipulationByTelegramLog.class);
    }

    @Override
    public OrderManipulationByTelegramLogDto toDto(OrderManipulationByTelegramLog entity) {
        return Objects.isNull(entity) ? null : mapper.map(entity, OrderManipulationByTelegramLogDto.class);
    }
}
