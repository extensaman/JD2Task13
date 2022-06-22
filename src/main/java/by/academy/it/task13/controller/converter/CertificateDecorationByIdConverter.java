package by.academy.it.task13.controller.converter;

import by.academy.it.task13.entity.CertificateDecoration;
import by.academy.it.task13.service.CertificateDecorationService;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CertificateDecorationByIdConverter implements Converter<String, CertificateDecoration> {
    private static final Logger LOGGER = LogManager.getLogger(CertificateDecorationByIdConverter.class);

    private final CertificateDecorationService service;

    @Override
    public CertificateDecoration convert(String id) {
        LOGGER.info("convert certDecorId " + id + "to CertificateDecoration");
        return service.findById(id).orElse(null);
    }
}
