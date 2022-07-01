package by.academy.it.task13.controller.admin;

import by.academy.it.task13.dto.CertificateOrderDto;
import by.academy.it.task13.service.CertificateOrderService;
import by.academy.it.task13.service.CertificateService;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(AdminConstant.ADMIN_GIFT_CERTIFICATE_ORDER_MAPPING)
@RequiredArgsConstructor
public class AdminCertificateOrderController {
    private static final Logger LOGGER = LogManager.getLogger(AdminCertificateOrderController.class);

    private final CertificateOrderService certificateOrderService;

    @GetMapping
    public String getGiftCertificateOrderPage(Model model) {
        LOGGER.info("getGiftCertificateOrderPage");
        model.addAttribute(AdminConstant.TITLE,
                AdminConstant.MENU_ADMIN_CERTIFICATE_ORDER_MESSAGE);
        model.addAttribute(AdminConstant.CERTIFICATE_ORDER_LIST,
                certificateOrderService.findAll());
        return AdminConstant.ADMIN_CERTIFICATE_ORDER_PAGE;
    }

    @PostMapping
    public String saveCertificateOrderChange(CertificateOrderDto certificateOrderDto) {
        LOGGER.info("saveCertificateOrderChange");
        certificateOrderService.save(certificateOrderDto);
        return "redirect:/admin/certificateorder";
    }

    @PostMapping(AdminConstant.DELETE_MAPPING)
    public String deleteCertificateOrder(CertificateOrderDto certificateOrderDto) {
        LOGGER.info("deleteCertificateOrder");
        certificateOrderService.delete(certificateOrderDto);
        return "redirect:/admin/certificate";
    }
}
