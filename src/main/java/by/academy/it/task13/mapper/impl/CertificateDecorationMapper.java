package by.academy.it.task13.mapper.impl;

import by.academy.it.task13.dto.CertificateDecorationDto;
import by.academy.it.task13.entity.CertificateDecoration;
import by.academy.it.task13.mapper.Mapper;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
@RequiredArgsConstructor
public class CertificateDecorationMapper implements Mapper<CertificateDecoration, CertificateDecorationDto> {
    private final ModelMapper mapper;

    @Override
    public CertificateDecoration toEntity(CertificateDecorationDto dto) {
        return Objects.isNull(dto) ? null : mapper.map(dto, CertificateDecoration.class);
    }

    @Override
    public CertificateDecorationDto toDto(CertificateDecoration entity) {
        return Objects.isNull(entity) ? null : mapper.map(entity, CertificateDecorationDto.class);
    }
}
