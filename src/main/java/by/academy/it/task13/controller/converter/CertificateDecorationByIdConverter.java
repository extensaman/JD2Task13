package by.academy.it.task13.controller.converter;

import by.academy.it.task13.dto.certificatedecoration.CertificateDecorationDto;
import by.academy.it.task13.service.CertificateDecorationService;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CertificateDecorationByIdConverter implements Converter<String, CertificateDecorationDto> {
    private static final Logger LOGGER = LogManager.getLogger(CertificateDecorationByIdConverter.class);

    private final CertificateDecorationService service;

    @Override
    public CertificateDecorationDto convert(String id) {
        LOGGER.info("convert CertificateDecoration id = " + id + " to CertificateDecorationDto");
        return service.findById(id).orElse(null);
    }
}
