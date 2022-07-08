package by.academy.it.task13.controller.admin;

import by.academy.it.task13.dto.CertificateOrderDto;
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
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping(AdminConstant.ADMIN_GIFT_CERTIFICATE_MAPPING)
//@SessionAttributes(names = {"deleteBan","certificateNameList"})
@RequiredArgsConstructor
public class AdminCertificateController {
    private static final Logger LOGGER = LogManager.getLogger(AdminCertificateController.class);

    private final CertificateService certificateService;
    private final CertificateTypeService certificateTypeService;
    private final CertificateOrderService certificateOrderService;
    private final ImageFileList imageFileList;

    @GetMapping
    public String getGiftCertificatePage(Model model) {
        LOGGER.info("getGiftCertificatePage");
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
        LOGGER.info("CertificateType = " + certificateDto.getCertificateType());
        certificateService.save(certificateDto);
        return "redirect:/admin/certificate";
    }

    @PostMapping(AdminConstant.DELETE_MAPPING)
    public String deleteCertificate(Model model, CertificateDto certificateDto) {
        LOGGER.info("deleteCertificate");
        List<CertificateOrderDto> ordersByCertificateId = certificateOrderService.findCertificateOrdersByCertificateId(certificateDto.getId());
        if(ordersByCertificateId.size() == 0) {
            certificateService.delete(certificateDto);
            return "redirect:/admin/certificate";
        }
        model.addAttribute("deleteBan", "true");
        List<String> collect = ordersByCertificateId.stream().map(certificateOrderDto -> certificateOrderDto.getCertificate().getName())
                .collect(Collectors.toList());
        model.addAttribute("certificateNameList", collect);
        LOGGER.info("deleteCertificate --- deleteBan is TRUE ---");
        return getGiftCertificatePage(model);
    }
}
