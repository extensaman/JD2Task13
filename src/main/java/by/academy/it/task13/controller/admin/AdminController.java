package by.academy.it.task13.controller.admin;

import by.academy.it.task13.controller.Constant;
import by.academy.it.task13.entity.CertificateDecoration;
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
    private final CertificateDecorationService certificateDecorationService;

    @GetMapping
    public String getMainAdminPage(Model model) {
        model.addAttribute(Constant.TITLE,
                Constant.MENU_ADMIN_MAIN_MESSAGE);
        return Constant.ADMIN_MAIN_PAGE;
    }



    @GetMapping(Constant.ADMIN_CERTIFICATE_DECORATION_MAPPING)
    public String getCertificateDeliveryPage(Model model) {
        model.addAttribute(Constant.TITLE,
                Constant.MENU_ADMIN_CERTIFICATE_DECORATION_MESSAGE);
        model.addAttribute(Constant.CERTIFICATE_DECORATION_LIST, certificateDecorationService.findAll());
        return Constant.ADMIN_CERTIFICATE_DECORATION_PAGE;
    }

    @PostMapping(Constant.ADMIN_CERTIFICATE_DECORATION_MAPPING)
    public String saveCertificateDecorationChange(@ModelAttribute CertificateDecoration certificateDecoration) {
        certificateDecorationService.save(certificateDecoration);
        return "redirect:/admin/certificatedecoration";
    }
}
