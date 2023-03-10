package by.academy.it.task13.mapper.impl.certificate;

import by.academy.it.task13.dto.certificate.CertificateDto;
import by.academy.it.task13.dto.certificate.CertificateNameDto;
import by.academy.it.task13.entity.Certificate;
import by.academy.it.task13.mapper.Mapper;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
@RequiredArgsConstructor
public class CertificateNameMapper implements Mapper<Certificate, CertificateNameDto> {

    private final ModelMapper mapper;

    @Override
    public Certificate toEntity(CertificateNameDto dto) {
        return Objects.isNull(dto) ? null : mapper.map(dto, Certificate.class);
    }

    @Override
    public CertificateNameDto toDto(Certificate entity) {
        return Objects.isNull(entity) ? null : mapper.map(entity, CertificateNameDto.class);
    }
}
