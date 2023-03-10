package by.academy.it.task13.controller.admin;

import by.academy.it.task13.dto.CertificateTypeDto;
import by.academy.it.task13.service.CertificateService;
import by.academy.it.task13.service.CertificateTypeService;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping(AdminConstant.ADMIN_CERTIFICATE_TYPE_MAPPING)
@RequiredArgsConstructor
public class AdminCertificateTypeController {
    private static final Logger logger = LogManager.getLogger(AdminCertificateTypeController.class);

    private final CertificateTypeService certificateTypeService;
    private final CertificateService certificateService;

    @GetMapping
    public String getCertificateTypePage(Model model) {
        logger.info("getCertificateTypePage");
        model.addAttribute(AdminConstant.TITLE,
                AdminConstant.MENU_ADMIN_CERTIFICATE_TYPE_MESSAGE);
        model.addAttribute(AdminConstant.CERTIFICATE_TYPE_LIST, certificateTypeService.findAll());
        return AdminConstant.ADMIN_CERTIFICATE_TYPE_PAGE;
    }

    @PostMapping
    public String saveCertificateType(CertificateTypeDto certificateTypeDto) {
        logger.info("saveCertificateType");
        certificateTypeService.saveCertificateTypeAndUpdateAllCertificate(certificateTypeDto);
        return AdminConstant.REDIRECT_ADMIN_CERTIFICATETYPE;
    }

    @PostMapping(AdminConstant.DELETE_MAPPING)
    public String deleteCertificateType(Model model, CertificateTypeDto certificateTypeDto) {
        logger.info("deleteCertificateType");
        List<String> certificateNameList = certificateService.findCertificateNamesByCertificateTypeId(certificateTypeDto.getId());
        if (certificateNameList.size() == AdminConstant.ZERO) {
            certificateTypeService.delete(certificateTypeDto);
            return AdminConstant.REDIRECT_ADMIN_CERTIFICATETYPE;
        }
        model.addAttribute(AdminConstant.DELETE_BAN, AdminConstant.TRUE);
        model.addAttribute(AdminConstant.CERTIFICATE_NAME_LIST, certificateNameList);
        return getCertificateTypePage(model);
    }
}
