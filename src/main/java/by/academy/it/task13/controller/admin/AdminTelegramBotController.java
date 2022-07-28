package by.academy.it.task13.controller.admin;

import by.academy.it.task13.dto.PhotoSessionDto;
import by.academy.it.task13.dto.TelegramSubscriberDto;
import by.academy.it.task13.service.TelegramOrderLogService;
import by.academy.it.task13.service.TelegramSubscriberService;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(AdminConstant.ADMIN_TELEGRAM_BOT_MAPPING)
@RequiredArgsConstructor
public class AdminTelegramBotController {
    private static final Logger logger = LogManager.getLogger(AdminTelegramBotController.class);

    private final TelegramSubscriberService subscriberService;
    private final TelegramOrderLogService logService;

    @GetMapping
    public String getTelegramBotPage(Model model){
        logger.info("getTelegramBotPage");
        model.addAttribute(AdminConstant.TITLE,
                AdminConstant.MENU_ADMIN_TELEGRAM_BOT_MESSAGE);
        model.addAttribute(AdminConstant.TELEGRAM_ORDER_LOG_DTO_LIST,
                        logService.findAll());
        model.addAttribute(AdminConstant.TELEGRAM_SUBSCRIBER_DTO_LIST,
                subscriberService.findAll());
        return AdminConstant.ADMIN_TELEGRAM_BOT_PAGE;
    }

    @PostMapping(AdminConstant.SUBSCRIBER_MAPPING)
    public String saveSubscriber(TelegramSubscriberDto telegramSubscriberDto) {
        logger.info("saveSubscriber");
        subscriberService.save(telegramSubscriberDto);
        return AdminConstant.REDIRECT_ADMIN_TELEGRAMBOT;
    }

    @PostMapping(AdminConstant.SUBSCRIBER_DELETE_MAPPING)
    public String deleteSubscriber(TelegramSubscriberDto telegramSubscriberDto) {
        logger.info("deleteSubscriber");
        subscriberService.delete(telegramSubscriberDto);
        return AdminConstant.REDIRECT_ADMIN_TELEGRAMBOT;
    }
}
