package by.academy.it.task13.controller;

import by.academy.it.task13.dto.CertificateOrderDto;
import by.academy.it.task13.service.CertificateDecorationService;
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
@SessionAttributes(names = "certificateOrderDto")
@RequiredArgsConstructor
public class OrderController {
    private static final Logger LOGGER = LogManager.getLogger(OrderController.class);

    private final CertificateDecorationService certificateDecorationService;

    @GetMapping
    public String orderCertificate(@ModelAttribute CertificateOrderDto certificateOrderDto, Model model) {
        LOGGER.info("orderCertificate");
        if (certificateOrderDto.getCertificate() == null) {
            return "redirect:/certificate";
        }
        Optional.ofNullable(certificateOrderDto.getCertificate())
                .ifPresent(certificate -> LOGGER.info(certificate.getPhotoFile()));
        model.addAttribute(Constant.ACTIVE_CERTIFICATE_DECORATION_LIST, certificateDecorationService.findAllActiveCertificateDecoration());
        model.addAttribute(Constant.CERTIFICATE_ORDER, certificateOrderDto);
        model.addAttribute(Constant.TITLE,
                Constant.TITLE_CERTIFICATE_ORDER_MESSAGE);
        return Constant.CERTIFICATE_ORDER_PAGE;
    }

    @ModelAttribute(name = "certificateOrderDto")
    public CertificateOrderDto certificateOrderDto() {
        return new CertificateOrderDto();
    }

    @PostMapping
    public String getOrderPage(Model model, @Valid CertificateOrderDto certificateOrderDto, Errors errors) {
        LOGGER.info("getOrderPage");
        model.addAttribute(Constant.TITLE,
                Constant.TITLE_ORDER_MESSAGE);
        if (errors.hasErrors()) {
            LOGGER.info("getOrderPage :: errors.hasErrors() " + errors.getFieldError().getField());
            model.addAttribute(Constant.ACTIVE_CERTIFICATE_DECORATION_LIST, certificateDecorationService.findAllActiveCertificateDecoration());
            return Constant.CERTIFICATE_ORDER_PAGE;
        }
        LOGGER.info("DESCRIPTION in ORDER is " + certificateOrderDto.getDescription());
        Optional.ofNullable(certificateOrderDto.getUser())
                .ifPresent(user -> LOGGER.info("USER in ORDER is " + user.getUsername()));
        Optional.ofNullable(certificateOrderDto.getCertificateDecoration())
                .ifPresent(cD ->
                        LOGGER.info("CERT_DECOR in ORDER is " + cD.getName()));

        return Constant.COACH_PAGE;
    }

}
