package by.academy.it.task13.controller.admin;

import by.academy.it.task13.entity.Certificate;
import by.academy.it.task13.service.CertificateService;
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
@RequestMapping(AdminConstant.ADMIN_GIFT_CERTIFICATE_MAPPING)
@RequiredArgsConstructor
public class AdminCertificateController {
    private static final Logger LOGGER = LogManager.getLogger(AdminCertificateController.class);

    @Autowired
    private final CertificateService certificateService;

    @GetMapping
    public String getGiftCertificatePage(Model model) {
        model.addAttribute(AdminConstant.TITLE,
                AdminConstant.MENU_ADMIN_GIFT_CERTIFICATE_MESSAGE);
        model.addAttribute(AdminConstant.GIFT_CERTIFICATE_LIST, certificateService.findAll());
        return AdminConstant.ADMIN_GIFT_CERTIFICATE_PAGE;
    }

    @PostMapping
    public String saveGiftCertificateChange(@ModelAttribute Certificate certificate) {
        certificateService.save(certificate);
        return "redirect:/admin/certificate";
    }
}
