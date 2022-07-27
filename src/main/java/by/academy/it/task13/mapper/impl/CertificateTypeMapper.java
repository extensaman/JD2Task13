package by.academy.it.task13.mapper.impl;

import by.academy.it.task13.dto.CertificateTypeDto;
import by.academy.it.task13.entity.CertificateType;
import by.academy.it.task13.mapper.Mapper;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
@RequiredArgsConstructor
public class CertificateTypeMapper implements Mapper<CertificateType, CertificateTypeDto> {
    private final ModelMapper mapper;

    @Override
    public CertificateType toEntity(CertificateTypeDto dto) {
        return Objects.isNull(dto) ? null : mapper.map(dto, CertificateType.class);
    }

    @Override
    public CertificateTypeDto toDto(CertificateType entity) {
        return Objects.isNull(entity) ? null : mapper.map(entity, CertificateTypeDto.class);
    }
}
