package by.academy.it.task13.controller.admin;

import by.academy.it.task13.dto.CertificateOrderDto;
import by.academy.it.task13.entity.OrderStatus;
import by.academy.it.task13.service.CertificateDecorationService;
import by.academy.it.task13.service.CertificateOrderService;
import by.academy.it.task13.service.CertificateService;
import by.academy.it.task13.service.UserService;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping(AdminConstant.ADMIN_GIFT_CERTIFICATE_ORDER_MAPPING)
@RequiredArgsConstructor
public class AdminCertificateOrderController {
    private static final Logger LOGGER = LogManager.getLogger(AdminCertificateOrderController.class);

    private final CertificateOrderService certificateOrderService;
    private final CertificateService certificateService;
    private final CertificateDecorationService certificateDecorationService;
    private final UserService userService;
    private final List<String> orderStatusList = Arrays.stream(OrderStatus.values())
                                                    .map(OrderStatus::toString)
                                                    .collect(Collectors.toList());
    @GetMapping
    public String getGiftCertificateOrderPage(@RequestParam(value = "pageNumber", required = false, defaultValue = "1") int pageNumber,
                                              @RequestParam(value = "size", required = false, defaultValue = "5") int size,
                                              @RequestParam(value = "sortField", required = false, defaultValue = "id") String sortField,
                                              @RequestParam(value = "sortDirection", required = false, defaultValue = "asc") String sortDirection,
                                              Model model) {
        LOGGER.info("getGiftCertificateOrderPage");

        model.addAttribute(AdminConstant.TITLE,
                AdminConstant.MENU_ADMIN_CERTIFICATE_ORDER_MESSAGE);

        model.addAttribute(AdminConstant.GIFT_CERTIFICATE_LIST, certificateService.findAll());
        model.addAttribute(AdminConstant.CERTIFICATE_DECORATION_LIST, certificateDecorationService.findAll());
        model.addAttribute(AdminConstant.ACTIVE_USER_LIST, userService.findAllActiveUser());
        model.addAttribute(AdminConstant.ORDER_STATUS_LIST, orderStatusList);
        model.addAttribute(AdminConstant.SORT_FIELD, sortField);
        model.addAttribute(AdminConstant.SORT_DIRECTION, sortDirection);

        String reverseSortDirection = AdminConstant.ASC.equals(sortDirection) ? AdminConstant.DESC : AdminConstant.ASC;
        model.addAttribute(AdminConstant.REVERSE_SORT_DIRECTION, reverseSortDirection);

        model.addAttribute(AdminConstant.CERTIFICATE_ORDER_PAGE,
                certificateOrderService.getExtendedPage(pageNumber, size, sortField, sortDirection));
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