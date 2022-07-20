package by.academy.it.task13.controller;

import by.academy.it.task13.dto.CertificateOrderDto;
import by.academy.it.task13.entity.CertificateOrder;
import by.academy.it.task13.mapper.Mapper;
import by.academy.it.task13.service.CertificateOrderService;
import by.academy.it.task13.service.MailSenderService;
import by.academy.it.task13.util.TelegramBot;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

@Controller
@RequestMapping("/payment")
@SessionAttributes(names = "certificateOrderDto")
@RequiredArgsConstructor
public class PaymentController {
    private static final Logger LOGGER = LogManager.getLogger(PaymentController.class);

    private final CertificateOrderService certificateOrderService;
    private final Mapper<CertificateOrder, CertificateOrderDto> mapper;
    private final TelegramBot bot;
    private final MailSenderService mailSenderService;

    @GetMapping
    public String getPaymentPage(Model model, @ModelAttribute CertificateOrderDto certificateOrderDto, SessionStatus sessionStatus) {
        LOGGER.info("getPaymentPage");
        CertificateOrder order = certificateOrderService.save(certificateOrderDto);
        CertificateOrderDto orderDto = mapper.toDto(order);

        bot.broadcastOrder(orderDto);
        mailSenderService.sendOrderInfoByMail(orderDto);
        sessionStatus.setComplete();
        return Constant.REDIRECT_PAYMENT_PAGE;
    }
}
