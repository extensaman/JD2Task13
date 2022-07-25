package by.academy.it.task13.controller.converter;

import by.academy.it.task13.dto.certificate.CertificateNameDto;
import by.academy.it.task13.service.CertificateService;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CertificateNameDtoByIdConverter implements Converter<String, CertificateNameDto> {
    private static final Logger LOGGER = LogManager.getLogger(CertificateNameDtoByIdConverter.class);

    private final CertificateService service;

    @Override
    public CertificateNameDto convert(String id) {
        LOGGER.info("convert Certificate id = " + id + " to CertificateNameDto");
        return service.findCertificateNameDtoById(id).orElse(null);
    }
}
