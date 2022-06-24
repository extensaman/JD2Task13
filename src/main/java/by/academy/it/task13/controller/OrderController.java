package by.academy.it.task13.controller;

import by.academy.it.task13.dto.CertificateOrderDto;
import by.academy.it.task13.service.CertificateDecorationService;
import by.academy.it.task13.service.CertificateOrderService;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import javax.validation.Valid;
import java.util.Optional;

@Controller
@RequestMapping(Constant.ORDER_MAPPING)
@SessionAttributes(names = Constant.CERTIFICATE_ORDER)
@RequiredArgsConstructor
public class OrderController {
    private static final Logger LOGGER = LogManager.getLogger(OrderController.class);

    private final CertificateDecorationService certificateDecorationService;
    private final CertificateOrderService certificateOrderService;

    @GetMapping
    public String orderCertificate(@ModelAttribute CertificateOrderDto certificateOrderDto, Model model) {
        LOGGER.info("orderCertificate");
        if (certificateOrderDto.getCertificate() == null) {
            return "redirect:/certificate";
        }
        Optional.ofNullable(certificateOrderDto.getCertificate())
                .ifPresent(certificate -> LOGGER.info(certificate.getPhotoFile()));
        model.addAttribute(Constant.ACTIVE_CERTIFICATE_DECORATION_LIST, certificateDecorationService.findAllActiveCertificateDecoration());
        model.addAttribute(Constant.TITLE,
                Constant.TITLE_CERTIFICATE_ORDER_MESSAGE);
        return Constant.CERTIFICATE_ORDER_PAGE;
    }

    @ModelAttribute(name = Constant.CERTIFICATE_ORDER)
    public CertificateOrderDto certificateOrderDto() {
        return new CertificateOrderDto();
    }

    @PostMapping
    public String getOrderPage(Model model, @Valid CertificateOrderDto certificateOrderDto, Errors errors) {
        LOGGER.info("getOrderPage");
        model.addAttribute(Constant.TITLE,
                Constant.TITLE_ORDER_MESSAGE);
        if (errors.hasErrors()) {
            LOGGER.info(errors.getFieldError().getField());
            LOGGER.info("Decor id = " + certificateOrderDto.getCertificateDecoration().getId());
            model.addAttribute(Constant.ACTIVE_CERTIFICATE_DECORATION_LIST, certificateDecorationService.findAllActiveCertificateDecoration());
            return Constant.CERTIFICATE_ORDER_PAGE;
        }
        LOGGER.info("DESCRIPTION in ORDER is " + certificateOrderDto.getDescription());
        Optional.ofNullable(certificateOrderDto.getUser())
                .ifPresent(user -> LOGGER.info("USER in ORDER is " + user.getUsername()));
        Optional.ofNullable(certificateOrderDto.getCertificateDecoration())
                .ifPresent(cD ->
                        LOGGER.info("CERT_DECOR in ORDER is " + cD.getName()));
        certificateOrderService.save(certificateOrderDto);
        LOGGER.info("certificateOrderDto SAVED");
        return Constant.COACH_PAGE;
    }

}
