package by.academy.it.task13.mapper.impl.certificate;

import by.academy.it.task13.dto.certificate.CertificateDto;
import by.academy.it.task13.entity.Certificate;
import by.academy.it.task13.mapper.Mapper;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
@RequiredArgsConstructor
public class CertificateMapper implements Mapper<Certificate, CertificateDto> {

    private final ModelMapper mapper;

    @Override
    public Certificate toEntity(CertificateDto dto) {
        return Objects.isNull(dto) ? null : mapper.map(dto, Certificate.class);
    }

    @Override
    public CertificateDto toDto(Certificate entity) {
        return Objects.isNull(entity) ? null : mapper.map(entity, CertificateDto.class);
    }
}
