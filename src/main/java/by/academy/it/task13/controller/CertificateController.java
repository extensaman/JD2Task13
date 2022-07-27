package by.academy.it.task13.controller;

import by.academy.it.task13.controller.admin.AdminConstant;
import by.academy.it.task13.dto.CertificateOrderDto;
import by.academy.it.task13.service.CertificateDecorationService;
import by.academy.it.task13.service.CertificateService;
import by.academy.it.task13.service.CertificateTypeService;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import javax.validation.Valid;
import java.util.Optional;

@Controller
@RequestMapping(Constant.CERTIFICATE_MAPPING)
@SessionAttributes(names = "certificateOrderDto")
@RequiredArgsConstructor
public class CertificateController {
    private static final Logger LOGGER = LogManager.getLogger(CertificateController.class);

    private final CertificateService certificateService;
    private final CertificateTypeService certificateTypeService;
    private final CertificateDecorationService certificateDecorationService;

    @ModelAttribute(name = Constant.CERTIFICATE_ORDER)
    public CertificateOrderDto certificateOrderDto() {
        return new CertificateOrderDto();
    }

    @GetMapping
    public String getCertificatePage(Model model) {
        LOGGER.info("getCertificatePage");
        model.addAttribute(Constant.ACTIVE_CERTIFICATE_LIST, certificateService.findAllActiveCertificate());
        model.addAttribute(Constant.ACTIVE_CERTIFICATE_TYPE_LIST, certificateTypeService.findAllActiveCertificateType());
        model.addAttribute(Constant.TITLE,
                Constant.TITLE_CERTIFICATE_MESSAGE);
        return Constant.CERTIFICATE_PAGE;
    }

    @PostMapping(Constant.ID_MAPPING)
    public String getCertificateWithSpecificType(@PathVariable String id, Model model) {
        LOGGER.info("Getting certificates with type's id = " + id);
        model.addAttribute(Constant.ACTIVE_CERTIFICATE_LIST,
                certificateService.findCertificatesByActivityTrueAndCertificateTypeId(id));
        model.addAttribute(Constant.ACTIVE_CERTIFICATE_TYPE_LIST,
                certificateTypeService.findAllActiveCertificateType());
        model.addAttribute(Constant.TITLE,
                Constant.TITLE_CERTIFICATE_MESSAGE);
        return Constant.CERTIFICATE_PAGE;
    }

    @GetMapping(Constant.DECORATION_MAPPING)
    public String getDecorationCertificateForm(@ModelAttribute CertificateOrderDto certificateOrderDto, Model model) {
        LOGGER.info("getDecorationCertificateForm");
        if (certificateOrderDto.getCertificate() == null) {
            return Constant.REDIRECT_CERTIFICATE;
        }
        Optional.ofNullable(certificateOrderDto.getCertificate())
                .ifPresent(certificate ->
                        LOGGER.info("Certificate name = " +
                                certificate.getName()));
        model.addAttribute(Constant.ACTIVE_CERTIFICATE_DECORATION_LIST, certificateDecorationService.findAllActiveCertificateDecoration());
        model.addAttribute(Constant.TITLE,
                Constant.TITLE_CERTIFICATE_ORDER_MESSAGE);
        return Constant.CERTIFICATE_DECORATION_PAGE;
    }

    @PostMapping(Constant.DECORATION_MAPPING)
    public String postDecoration(@ModelAttribute CertificateOrderDto certificateOrderDto) {
        LOGGER.info("postDecoration");
        if (certificateOrderDto.getCertificateDecoration().isDeliveryNecessity()) {
            certificateOrderDto.setDetails(null);
        }
        return Constant.REDIRECT_CERTIFICATE_ADDITIONAL;
    }

    @GetMapping(Constant.ADDITIONAL_MAPPING)
    public String getCertificateAdditionalDataForm(@ModelAttribute CertificateOrderDto certificateOrderDto, Model model) {
        LOGGER.info("getCertificateAdditionalDataForm");
        if (certificateOrderDto.getCertificateDecoration() == null) {
            return Constant.REDIRECT_CERTIFICATE_DECORATION;
        }
        model.addAttribute(Constant.TITLE,
                Constant.TITLE_CERTIFICATE_ORDER_MESSAGE);
        return Constant.CERTIFICATE_ADDITIONAL_DATA_PAGE;
    }

    @PostMapping(Constant.ADDITIONAL_MAPPING)
    public String postAdditionalData(Model model,
                                     @Valid CertificateOrderDto certificateOrderDto,
                                     Errors errors) {
        LOGGER.info("postAdditionalData");
        model.addAttribute(Constant.TITLE,
                Constant.TITLE_ORDER_MESSAGE);
        if (errors.hasErrors()) {
            LOGGER.info("Error field: " + Optional.ofNullable(errors.getFieldError()).map(FieldError::getField));
            return Constant.CERTIFICATE_ADDITIONAL_DATA_PAGE;
        }
        return AdminConstant.REDIRECT_PAYMENT;
    }
}
