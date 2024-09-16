package by.academy.it.task13.mapper.impl.certificatedecoration;

import by.academy.it.task13.dto.certificatedecoration.CertificateDecorationDto;
import by.academy.it.task13.entity.CertificateDecoration;
import by.academy.it.task13.mapper.Mapper;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
@RequiredArgsConstructor
public class CertificateDecorationMapper implements Mapper<CertificateDecoration, CertificateDecorationDto> {
    private static final Logger logger = LogManager.getLogger(CertificateDecorationMapper.class);
    private final ModelMapper mapper;

    @Override
    public CertificateDecoration toEntity(CertificateDecorationDto dto) {
        logger.info("Mapping CertificateDecorationDto to CertificateDecoration");
        return Objects.isNull(dto) ? null : mapper.map(dto, CertificateDecoration.class);
    }

    @Override
    public CertificateDecorationDto toDto(CertificateDecoration entity) {
        logger.info("Mapping CertificateDecoration to CertificateDecorationDto");
        return Objects.isNull(entity) ? null : mapper.map(entity, CertificateDecorationDto.class);
    }
}
