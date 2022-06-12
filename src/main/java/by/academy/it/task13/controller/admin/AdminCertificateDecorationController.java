package by.academy.it.task13.controller.admin;

import by.academy.it.task13.dto.CertificateDecorationDto;
import by.academy.it.task13.service.CertificateDecorationService;
import by.academy.it.task13.util.ImageFileList;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(AdminConstant.ADMIN_CERTIFICATE_DECORATION_MAPPING)
@RequiredArgsConstructor
public class AdminCertificateDecorationController {
    private static final Logger LOGGER = LogManager.getLogger(AdminCertificateDecorationController.class);

    private final CertificateDecorationService certificateDecorationService;
    private final ImageFileList imageFileList;

    @GetMapping
    public String getCertificateDeliveryPage(Model model) {
        LOGGER.info("getCertificateDeliveryPage");
        model.addAttribute(AdminConstant.TITLE,
                AdminConstant.MENU_ADMIN_CERTIFICATE_DECORATION_MESSAGE);
        model.addAttribute(AdminConstant.CERTIFICATE_DECORATION_LIST, certificateDecorationService.findAll());
        model.addAttribute(AdminConstant.IMAGE_FILE_LIST,
                imageFileList.getImageFileList());
        return AdminConstant.ADMIN_CERTIFICATE_DECORATION_PAGE;
    }

    @PostMapping
    public String saveCertificateDecoration(CertificateDecorationDto certificateDecorationDto) {
        LOGGER.info("saveCertificateDecoration");
        certificateDecorationService.save(certificateDecorationDto);
        return "redirect:/admin/certificatedecoration";
    }

    @PostMapping(AdminConstant.DELETE_MAPPING)
    public String deleteCertificateDecoration(CertificateDecorationDto certificateDecorationDto) {
        LOGGER.info("deleteCertificateDecoration");
        certificateDecorationService.delete(certificateDecorationDto);
        return "redirect:/admin/certificatedecoration";
    }
}
