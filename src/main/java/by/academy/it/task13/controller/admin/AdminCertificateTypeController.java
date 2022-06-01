package by.academy.it.task13.controller.admin;

import by.academy.it.task13.service.CertificateService;
import by.academy.it.task13.service.CertificateTypeService;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(AdminConstant.ADMIN_CERTIFICATE_TYPE_MAPPING)
@RequiredArgsConstructor
public class AdminCertificateTypeController {

    private static final Logger LOGGER = LogManager.getLogger(AdminCertificateTypeController.class);

    @Autowired
    private final CertificateTypeService certificateTypeService;
    @Autowired
    private final CertificateService certificateService;

    @GetMapping
    public String getCertificateTypePage(Model model) {
        model.addAttribute(AdminConstant.TITLE,
                AdminConstant.MENU_ADMIN_CERTIFICATE_TYPE_MESSAGE);
        model.addAttribute(AdminConstant.CERTIFICATE_TYPE_LIST, certificateTypeService.findAll());
        return AdminConstant.ADMIN_CERTIFICATE_TYPE_PAGE;
    }

    @PostMapping("/{id}")
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
}
