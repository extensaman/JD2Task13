package by.academy.it.task13.controller.admin;

import by.academy.it.task13.dto.CertificateOrderDto;
import by.academy.it.task13.entity.OrderStatus;
import by.academy.it.task13.service.CertificateDecorationService;
import by.academy.it.task13.service.CertificateOrderService;
import by.academy.it.task13.service.CertificateService;
import by.academy.it.task13.service.MailSenderService;
import by.academy.it.task13.service.UserService;
import by.academy.it.task13.service.specification.filter.CertificateOrderFilter;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

@Controller
@RequestMapping(AdminConstant.ADMIN_GIFT_CERTIFICATE_ORDER_MAPPING)
@SessionAttributes(names = AdminConstant.CERTIFICATE_ORDER_FILTER)
@RequiredArgsConstructor
public class AdminCertificateOrderController {
    private static final Logger LOGGER = LogManager.getLogger(AdminCertificateOrderController.class);

    private final CertificateOrderService certificateOrderService;
    private final CertificateService certificateService;
    private final CertificateDecorationService certificateDecorationService;
    private final UserService userService;
    private final MailSenderService mailSenderService;


    @ModelAttribute(name = AdminConstant.CERTIFICATE_ORDER_FILTER)
    public CertificateOrderFilter certificateOrderFilter() {
        return new CertificateOrderFilter();
    }

    @GetMapping
    public String getGiftCertificateOrderPage(@RequestParam(value = "pageNumber", required = false, defaultValue = "1") int pageNumber,
                                              @RequestParam(value = "size", required = false, defaultValue = "5") int size,
                                              @RequestParam(value = "sortField", required = false, defaultValue = "id") String sortField,
                                              @RequestParam(value = "sortDirection", required = false, defaultValue = "asc") String sortDirection,
                                              @ModelAttribute CertificateOrderFilter certificateOrderFilter,
                                              Model model) {
        LOGGER.info("getGiftCertificateOrderPage");
        model.addAttribute(AdminConstant.TITLE,
                AdminConstant.MENU_ADMIN_CERTIFICATE_ORDER_MESSAGE);

        model.addAttribute(AdminConstant.CERTIFICATE_NAME_DTO_LIST,
                certificateService.findAllCertificateNameDto());

        model.addAttribute(AdminConstant.CERTIFICATE_DECORATION_NAME_DTO_LIST,
                certificateDecorationService.findAllCertificateDecorationNameDto());

        model.addAttribute(AdminConstant.USER_NAME_DTO_LIST,
                userService.findAllUserNameDto());

        model.addAttribute(AdminConstant.ORDER_STATUS_LIST, OrderStatus.values());
        model.addAttribute(AdminConstant.SORT_FIELD, sortField);
        model.addAttribute(AdminConstant.SORT_DIRECTION, sortDirection);

        String reverseSortDirection = AdminConstant.ASC.equals(sortDirection) ? AdminConstant.DESC : AdminConstant.ASC;
        model.addAttribute(AdminConstant.REVERSE_SORT_DIRECTION, reverseSortDirection);

        model.addAttribute(AdminConstant.CERTIFICATE_ORDER_PAGE,
                certificateOrderService.getExtendedPage(certificateOrderFilter, pageNumber, size, sortField, sortDirection));
        return AdminConstant.ADMIN_CERTIFICATE_ORDER_PAGE;
    }

    @PostMapping
    public String saveCertificateOrder(CertificateOrderDto certificateOrderDto, boolean mailNeedance) {
        LOGGER.info("saveCertificateOrder");
        if(mailNeedance){
            mailSenderService.sendOrderInfoByMail(certificateOrderDto);
        }
        certificateOrderService.save(certificateOrderDto);
        return AdminConstant.REDIRECT_ADMIN_CERTIFICATEORDER_PAGE;
    }

    @PostMapping(AdminConstant.DELETE_MAPPING)
    public String deleteCertificateOrder(CertificateOrderDto certificateOrderDto) {
        LOGGER.info("deleteCertificateOrder");
        certificateOrderService.delete(certificateOrderDto);
        return AdminConstant.REDIRECT_ADMIN_CERTIFICATEORDER_PAGE;
    }

    @PostMapping(AdminConstant.RESET_FILTER_MAPPING)
    public String resetCertificateOrderFilter(SessionStatus sessionStatus) {
        LOGGER.info("resetCertificateOrderFilter");
        sessionStatus.setComplete();
        return AdminConstant.REDIRECT_ADMIN_CERTIFICATEORDER_PAGE;
    }
}
