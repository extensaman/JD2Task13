package by.academy.it.task13.controller;

import by.academy.it.task13.entity.Certificate;
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
                    type.setActivity(!type.isActivity());
                    certificateTypeService.save(type);
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

    @PostMapping(Constant.ADMIN_GIFT_CERTIFICATE_MAPPING + "/{id}")
    public String saveGiftCertificateChange(@PathVariable String id) {
        LOGGER.info("Changing state of usage of certificate with id = " + id);

        return "redirect:/admin/certificate";
    }

    @PostMapping(Constant.ADMIN_GIFT_CERTIFICATE_MAPPING)
    public String saveGiftCertificateChange777(@ModelAttribute Certificate certificate) {
        LOGGER.info("Certificate id " + certificate.getId());
        LOGGER.info("Certificate type " + certificate.getCertificateType().getName());
        LOGGER.info("Certificate name " + certificate.getName());
        LOGGER.info("Certificate horseCount " + certificate.getHorseCount());
        LOGGER.info("Certificate duration " + certificate.getDuration());
        LOGGER.info("Certificate price " + certificate.getPrice());
        LOGGER.info("Certificate photographerIncluded " + certificate.isPhotographerIncluded());

        return "redirect:/admin/certificate";
    }
}
