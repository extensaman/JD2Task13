package by.academy.it.task13.controller.admin;

import by.academy.it.task13.dto.certificate.CertificateDto;
import by.academy.it.task13.service.CertificateOrderService;
import by.academy.it.task13.service.CertificateService;
import by.academy.it.task13.service.CertificateTypeService;
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
@RequestMapping(AdminConstant.ADMIN_GIFT_CERTIFICATE_MAPPING)
@RequiredArgsConstructor
public class AdminCertificateController {
    private static final Logger logger = LogManager.getLogger(AdminCertificateController.class);

    private final CertificateService certificateService;
    private final CertificateTypeService certificateTypeService;
    private final CertificateOrderService certificateOrderService;
    private final ImageFileList imageFileList;

    @GetMapping
    public String getGiftCertificatePage(Model model) {
        logger.info("getGiftCertificatePage");
        model.addAttribute(AdminConstant.TITLE,
                AdminConstant.MENU_ADMIN_GIFT_CERTIFICATE_MESSAGE);
        model.addAttribute(AdminConstant.GIFT_CERTIFICATE_LIST, certificateService.findAll());
        model.addAttribute(AdminConstant.CERTIFICATE_TYPE_LIST, certificateTypeService.findAll());
        model.addAttribute(AdminConstant.IMAGE_FILE_LIST,
                imageFileList.getImageFileList());
        return AdminConstant.ADMIN_GIFT_CERTIFICATE_PAGE;
    }

    @PostMapping
    public String saveGiftCertificateChange(CertificateDto certificateDto) {
        logger.info("saveGiftCertificateChange");
        certificateService.save(certificateDto);
        return AdminConstant.REDIRECT_ADMIN_CERTIFICATE_PAGE;
    }

    @PostMapping(AdminConstant.DELETE_MAPPING)
    public String deleteCertificate(Model model, CertificateDto certificateDto) {
        logger.info("deleteCertificate");
        List<Long> certificateOrderIdList = certificateOrderService.findCertificateOrderIdsByCertificateId(certificateDto.getId());
        if (certificateOrderIdList.size() == AdminConstant.ZERO) {
            certificateService.delete(certificateDto);
            return AdminConstant.REDIRECT_ADMIN_CERTIFICATE_PAGE;
        }
        model.addAttribute(AdminConstant.DELETE_BAN, AdminConstant.TRUE);
        model.addAttribute(AdminConstant.CERTIFICATE_ORDER_ID_LIST, certificateOrderIdList);
        return getGiftCertificatePage(model);
    }
}
