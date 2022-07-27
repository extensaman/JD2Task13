package by.academy.it.task13.mapper.impl;

import by.academy.it.task13.dto.SubscriptionDto;
import by.academy.it.task13.entity.Subscription;
import by.academy.it.task13.mapper.Mapper;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
@RequiredArgsConstructor
public class SubscriptionMapper implements Mapper<Subscription, SubscriptionDto> {
    private static final Logger LOGGER = LogManager.getLogger(SubscriptionMapper.class);
    private final ModelMapper mapper;

    @Override
    public Subscription toEntity(SubscriptionDto dto) {
        LOGGER.info("Mapping SubscriptionDto to Subscription");
        return Objects.isNull(dto) ? null : mapper.map(dto, Subscription.class);
    }

    @Override
    public SubscriptionDto toDto(Subscription entity) {
        LOGGER.info("Mapping Subscription to SubscriptionDto");
        return Objects.isNull(entity) ? null : mapper.map(entity, SubscriptionDto.class);
    }
}
