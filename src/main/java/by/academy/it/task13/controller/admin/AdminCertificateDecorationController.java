package by.academy.it.task13.controller.admin;

import by.academy.it.task13.dto.certificatedecoration.CertificateDecorationDto;
import by.academy.it.task13.service.CertificateDecorationService;
import by.academy.it.task13.service.CertificateOrderService;
import by.academy.it.task13.util.ImageFileList;
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
@RequestMapping(AdminConstant.ADMIN_CERTIFICATE_DECORATION_MAPPING)
@RequiredArgsConstructor
public class AdminCertificateDecorationController {

    private final CertificateDecorationService certificateDecorationService;
    private final CertificateOrderService certificateOrderService;
    private final ImageFileList imageFileList;

    @GetMapping
    public String getCertificateDecorationPage(Model model) {
        model.addAttribute(AdminConstant.TITLE,
                AdminConstant.MENU_ADMIN_CERTIFICATE_DECORATION_MESSAGE);
        model.addAttribute(AdminConstant.CERTIFICATE_DECORATION_LIST, certificateDecorationService.findAll());
        model.addAttribute(AdminConstant.IMAGE_FILE_LIST,
                imageFileList.getImageFileList());
        return AdminConstant.ADMIN_CERTIFICATE_DECORATION_PAGE;
    }

    @PostMapping
    public String saveCertificateDecoration(CertificateDecorationDto certificateDecorationDto) {
        certificateDecorationService.save(certificateDecorationDto);
        return AdminConstant.REDIRECT_ADMIN_CERTIFICATEDECORATION_PAGE;
    }

    @PostMapping(AdminConstant.DELETE_MAPPING)
    public String deleteCertificateDecoration(Model model, CertificateDecorationDto certificateDecorationDto) {
        List<Long> certificateOrderIdList = certificateOrderService.findCertificateOrderIdsByCertificateDecorationId(certificateDecorationDto.getId());
        if (certificateOrderIdList.size() == AdminConstant.ZERO) {
            certificateDecorationService.delete(certificateDecorationDto);
            return AdminConstant.REDIRECT_ADMIN_CERTIFICATEDECORATION_PAGE;
        }
        model.addAttribute(AdminConstant.DELETE_BAN, AdminConstant.TRUE);
        model.addAttribute(AdminConstant.CERTIFICATE_ORDER_ID_LIST, certificateOrderIdList);
        return getCertificateDecorationPage(model);
    }
}
