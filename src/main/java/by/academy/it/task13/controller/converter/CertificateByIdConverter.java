package by.academy.it.task13.controller.converter;

import by.academy.it.task13.entity.Certificate;
import by.academy.it.task13.service.CertificateService;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CertificateByIdConverter implements Converter<String, Certificate> {
    private static final Logger LOGGER = LogManager.getLogger(CertificateByIdConverter.class);

    private final CertificateService service;

    @Override
    public Certificate convert(String id) {
        LOGGER.info("convert CertificateId " + id + "to Certificate");
        return service.findById(id).orElse(null);
    }
}
