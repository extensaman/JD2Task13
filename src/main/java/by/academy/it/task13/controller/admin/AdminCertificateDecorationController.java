package by.academy.it.task13.controller.admin;

import by.academy.it.task13.entity.CertificateDecoration;
import by.academy.it.task13.service.CertificateDecorationService;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(AdminConstant.ADMIN_CERTIFICATE_DECORATION_MAPPING)
@RequiredArgsConstructor
public class AdminCertificateDecorationController {
    private static final Logger LOGGER = LogManager.getLogger(AdminCertificateDecorationController.class);

    @Autowired
    private final CertificateDecorationService certificateDecorationService;

    @GetMapping
    public String getCertificateDeliveryPage(Model model) {
        model.addAttribute(AdminConstant.TITLE,
                AdminConstant.MENU_ADMIN_CERTIFICATE_DECORATION_MESSAGE);
        model.addAttribute(AdminConstant.CERTIFICATE_DECORATION_LIST, certificateDecorationService.findAll());
        return AdminConstant.ADMIN_CERTIFICATE_DECORATION_PAGE;
    }

    @PostMapping
    public String saveCertificateDecoration(@ModelAttribute CertificateDecoration certificateDecoration) {
        certificateDecorationService.save(certificateDecoration);
        return "redirect:/admin/certificatedecoration";
    }

    @PostMapping("/delete")
    public String deleteCertificateDecoration(@ModelAttribute CertificateDecoration certificateDecoration) {
        certificateDecorationService.delete(certificateDecoration);
        return "redirect:/admin/certificatedecoration";
    }
}
