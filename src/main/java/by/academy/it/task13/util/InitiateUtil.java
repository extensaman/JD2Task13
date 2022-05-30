package by.academy.it.task13.util;

import by.academy.it.task13.entity.Certificate;
import by.academy.it.task13.entity.CertificateType;
import by.academy.it.task13.entity.CertificateDecoration;
import by.academy.it.task13.service.CertificateDecorationService;
import by.academy.it.task13.service.CertificateService;
import by.academy.it.task13.service.CertificateTypeService;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
@RequiredArgsConstructor
public class InitiateUtil implements CommandLineRunner {

    private static final Logger LOGGER = LogManager.getLogger(InitiateUtil.class);

    @Autowired
    private final CertificateTypeService certificateTypeService;
    @Autowired
    private final CertificateService certificateService;
    @Autowired
    private final CertificateDecorationService certificateDecorationService;

    @Override
    public void run(String[] args) throws Exception {
        CertificateType certificateType01 = CertificateType.builder()
                .activity(true)
                .name("certificate_type.name.single")
                .build();
        CertificateType certificateType02 = CertificateType.builder()
                .activity(false)
                .name("certificate_type.name.pair")
                .build();
        CertificateType certificateType03 = CertificateType.builder()
                .activity(true)
                .name("certificate_type.name.family")
                .build();
        CertificateType certificateType04 = CertificateType.builder()
                .activity(true)
                .name("certificate_type.name.children")
                .build();
        List<CertificateType> certificateTypeList = new ArrayList<>(
                Arrays.asList(
                        certificateType01,
                        certificateType02,
                        certificateType03,
                        certificateType04
                )
        );
        certificateTypeService.saveAll(certificateTypeList);
        LOGGER.info("Initialization of 'CertificateType' done");

        Certificate certificate01 = Certificate.builder()
                .activity(true)
                .certificateType(certificateType01)
                .name("certificate.name.rider")
                .description("certificate.description.rider")
                .horseCount(1)
                .duration(1.0)
                .price(BigDecimal.valueOf(52.5))
                .photographerIncluded(false)
                .build();
        Certificate certificate02 = Certificate.builder()
                .activity(true)
                .certificateType(certificateType01)
                .name("certificate.name.riderplus")
                .description("certificate.description.riderplus")
                .horseCount(1)
                .duration(2.0)
                .price(BigDecimal.valueOf(80.0))
                .photographerIncluded(true)
                .build();
        Certificate certificate03 = Certificate.builder()
                .activity(true)
                .certificateType(certificateType01)
                .name("certificate.name.forest")
                .description("certificate.description.forest")
                .horseCount(1)
                .duration(1.5)
                .price(BigDecimal.valueOf(75.0))
                .photographerIncluded(false)
                .build();
        certificateService.saveAll(
                List.of(certificate01,certificate02,certificate03));
        LOGGER.info("Initialization of 'Certificate' done");

        CertificateDecoration certificateDecoration01 = CertificateDecoration.builder()
                .activity(true)
                .name("certificate_decoration.name.electronic")
                .description("certificate_decoration.description.electronic")
                .price(BigDecimal.ZERO)
                .build();
        CertificateDecoration certificateDecoration02 = CertificateDecoration.builder()
                .activity(true)
                .name("certificate_decoration.name.envelop")
                .description("certificate_decoration.description.envelop")
                .price(BigDecimal.TEN)
                .build();
        CertificateDecoration certificateDecoration03 = CertificateDecoration.builder()
                .activity(true)
                .name("certificate_decoration.name.card")
                .description("certificate_decoration.description.card")
                .price(BigDecimal.valueOf(15.0))
                .build();
        CertificateDecoration certificateDecoration04 = CertificateDecoration.builder()
                .activity(true)
                .name("certificate_decoration.name.gift_basket")
                .description("certificate_decoration.description.gift_basket")
                .price(BigDecimal.valueOf(30.0))
                .build();
        certificateDecorationService.saveAll(
                List.of(certificateDecoration01,
                        certificateDecoration02,
                        certificateDecoration03,
                        certificateDecoration04));
        LOGGER.info("Initialization of 'CertificateDecoration' done");
    }
}
