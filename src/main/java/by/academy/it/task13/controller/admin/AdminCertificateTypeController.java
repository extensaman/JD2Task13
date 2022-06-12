package by.academy.it.task13.controller.admin;

import by.academy.it.task13.dto.CertificateTypeDto;
import by.academy.it.task13.service.CertificateTypeService;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(AdminConstant.ADMIN_CERTIFICATE_TYPE_MAPPING)
@RequiredArgsConstructor
public class AdminCertificateTypeController {
    private static final Logger LOGGER = LogManager.getLogger(AdminCertificateTypeController.class);

    private final CertificateTypeService certificateTypeService;

    @GetMapping
    public String getCertificateTypePage(Model model) {
        LOGGER.info("getCertificateTypePage");
        model.addAttribute(AdminConstant.TITLE,
                AdminConstant.MENU_ADMIN_CERTIFICATE_TYPE_MESSAGE);
        model.addAttribute(AdminConstant.CERTIFICATE_TYPE_LIST, certificateTypeService.findAll());
        return AdminConstant.ADMIN_CERTIFICATE_TYPE_PAGE;
    }

    @PostMapping
    public String saveCertificateType(CertificateTypeDto certificateTypeDto) {
        LOGGER.info("saveCertificateType");
        certificateTypeService.saveCertificateTypeAndUpdateAllCertificate(certificateTypeDto);
        return "redirect:/admin/certificatetype";
    }

    @PostMapping(AdminConstant.DELETE_MAPPING)
    public String deleteCertificateType(CertificateTypeDto certificateTypeDto) {
        LOGGER.info("deleteCertificateType");
        certificateTypeService.delete(certificateTypeDto);
        return "redirect:/admin/certificatetype";
    }
}
