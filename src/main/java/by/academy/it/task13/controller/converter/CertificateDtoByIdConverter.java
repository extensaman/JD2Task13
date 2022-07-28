package by.academy.it.task13.controller.converter;

import by.academy.it.task13.dto.certificate.CertificateDto;
import by.academy.it.task13.service.CertificateService;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CertificateDtoByIdConverter implements Converter<String, CertificateDto> {
    private static final Logger logger = LogManager.getLogger(CertificateDtoByIdConverter.class);

    private final CertificateService service;

    @Override
    public CertificateDto convert(String id) {
        logger.info("convert Certificate id = " + id + " to CertificateDto");
        return service.findCertificateDtoById(id).orElse(null);
    }
}
