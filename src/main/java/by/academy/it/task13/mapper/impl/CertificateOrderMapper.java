package by.academy.it.task13.mapper.impl;

import by.academy.it.task13.dto.CertificateOrderDto;
import by.academy.it.task13.entity.CertificateOrder;
import by.academy.it.task13.mapper.Mapper;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
@RequiredArgsConstructor
public class CertificateOrderMapper implements Mapper<CertificateOrder, CertificateOrderDto> {
    private static final Logger logger = LogManager.getLogger(CertificateOrderMapper.class);
    private final ModelMapper mapper;

    @Override
    public CertificateOrder toEntity(CertificateOrderDto dto) {
        logger.info("Mapping CertificateOrderDto to CertificateOrder");
        return Objects.isNull(dto) ? null : mapper.map(dto, CertificateOrder.class);
    }

    @Override
    public CertificateOrderDto toDto(CertificateOrder entity) {
        logger.info("Mapping CertificateOrder to CertificateOrderDto");
        return Objects.isNull(entity) ? null : mapper.map(entity, CertificateOrderDto.class);
    }
}
