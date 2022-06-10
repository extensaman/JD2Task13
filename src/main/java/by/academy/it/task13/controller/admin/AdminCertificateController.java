package by.academy.it.task13.controller.admin;

import by.academy.it.task13.dto.CertificateDto;
import by.academy.it.task13.service.CertificateService;
import by.academy.it.task13.service.CertificateTypeService;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(AdminConstant.ADMIN_GIFT_CERTIFICATE_MAPPING)
@RequiredArgsConstructor
public class AdminCertificateController {
    private static final Logger LOGGER = LogManager.getLogger(AdminCertificateController.class);

    private final CertificateService certificateService;
    private final CertificateTypeService certificateTypeService;

    @GetMapping
    public String getGiftCertificatePage(Model model) {
        model.addAttribute(AdminConstant.TITLE,
                AdminConstant.MENU_ADMIN_GIFT_CERTIFICATE_MESSAGE);
        model.addAttribute(AdminConstant.GIFT_CERTIFICATE_LIST, certificateService.findAll());
        model.addAttribute(AdminConstant.CERTIFICATE_TYPE_LIST, certificateTypeService.findAll());
        return AdminConstant.ADMIN_GIFT_CERTIFICATE_PAGE;
    }

    @PostMapping
    public String saveGiftCertificateChange(@ModelAttribute CertificateDto certificateDto) {
        LOGGER.info("CertificatType = " + certificateDto.getCertificateType());
        certificateService.save(certificateDto);
        return "redirect:/admin/certificate";
    }

    @PostMapping(AdminConstant.DELETE_MAPPING)
    public String deleteCertificate(@ModelAttribute CertificateDto certificateDto) {
        LOGGER.info("deleteCertificate");
        certificateService.delete(certificateDto);
        return "redirect:/admin/certificate";
    }
}
