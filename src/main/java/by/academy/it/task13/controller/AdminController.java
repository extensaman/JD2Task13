package by.academy.it.task13.controller;

import by.academy.it.task13.entity.Certificate;
import by.academy.it.task13.service.CertificateDecorationService;
import by.academy.it.task13.service.CertificateService;
import by.academy.it.task13.service.CertificateTypeService;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(Constant.ADMIN_MAIN_MAPPING)
@RequiredArgsConstructor
public class AdminController {
    private static final Logger LOGGER = LogManager.getLogger(AdminController.class);

    @Autowired
    private final CertificateTypeService certificateTypeService;
    @Autowired
    private final CertificateService certificateService;
    @Autowired
    private final CertificateDecorationService certificateDecorationService;

    @GetMapping
    public String getMainAdminPage(Model model) {
        model.addAttribute(Constant.TITLE,
                Constant.MENU_ADMIN_MAIN_MESSAGE);
        return Constant.ADMIN_MAIN_PAGE;
    }

    @GetMapping(Constant.ADMIN_CERTIFICATE_TYPE_MAPPING)
    public String getCertificateTypePage(Model model) {
        model.addAttribute(Constant.TITLE,
                Constant.MENU_ADMIN_CERTIFICATE_TYPE_MESSAGE);
        model.addAttribute(Constant.CERTIFICATE_TYPE_LIST, certificateTypeService.findAll());
        return Constant.ADMIN_CERTIFICATE_TYPE_PAGE;
    }

    @PostMapping(Constant.ADMIN_CERTIFICATE_TYPE_MAPPING + "/{id}")
    public String saveCertificateTypeChange(@PathVariable String id) {
        LOGGER.info("Changing state of usage of certificateType with id = " + id);
        certificateTypeService.findById(id)
                .ifPresent(type -> {
                    boolean typeActivity = type.isActivity();
                    type.setActivity(!typeActivity);
                    certificateTypeService.save(type);
                    certificateService.findAll().stream()
                            .filter(certificate -> certificate.getCertificateType().equals(type))
                            .peek(certificate -> certificate.setActivity(!typeActivity))
                            .forEach(certificateService::save);
                });
        return "redirect:/admin/certificatetype";
    }

    @GetMapping(Constant.ADMIN_GIFT_CERTIFICATE_MAPPING)
    public String getGiftCertificatePage(Model model) {
        model.addAttribute(Constant.TITLE,
                Constant.MENU_ADMIN_GIFT_CERTIFICATE_MESSAGE);
        model.addAttribute(Constant.GIFT_CERTIFICATE_LIST, certificateService.findAll());
        return Constant.ADMIN_GIFT_CERTIFICATE_PAGE;
    }

    @PostMapping(Constant.ADMIN_GIFT_CERTIFICATE_MAPPING)
    public String saveGiftCertificateChange(@ModelAttribute Certificate certificate) {
        certificateService.save(certificate);
        return "redirect:/admin/certificate";
    }

    @GetMapping(Constant.ADMIN_CERTIFICATE_DECORATION_MAPPING)
    public String getCertificateDeliveryPage(Model model) {
        model.addAttribute(Constant.TITLE,
                Constant.MENU_ADMIN_CERTIFICATE_DECORATION_MESSAGE);
        model.addAttribute(Constant.CERTIFICATE_DECORATION_LIST, certificateDecorationService.findAll());
        return Constant.ADMIN_CERTIFICATE_DECORATION_PAGE;
    }
}
