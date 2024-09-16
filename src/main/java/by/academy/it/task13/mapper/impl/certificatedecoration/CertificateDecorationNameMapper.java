package by.academy.it.task13.mapper.impl.certificatedecoration;

import by.academy.it.task13.dto.certificatedecoration.CertificateDecorationDto;
import by.academy.it.task13.dto.certificatedecoration.CertificateDecorationNameDto;
import by.academy.it.task13.entity.CertificateDecoration;
import by.academy.it.task13.mapper.Mapper;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
@RequiredArgsConstructor
public class CertificateDecorationNameMapper implements Mapper<CertificateDecoration, CertificateDecorationNameDto> {
    private final ModelMapper mapper;

    @Override
    public CertificateDecoration toEntity(CertificateDecorationNameDto dto) {
        return Objects.isNull(dto) ? null : mapper.map(dto, CertificateDecoration.class);
    }

    @Override
    public CertificateDecorationNameDto toDto(CertificateDecoration entity) {
        return Objects.isNull(entity) ? null : mapper.map(entity, CertificateDecorationNameDto.class);
    }
}
