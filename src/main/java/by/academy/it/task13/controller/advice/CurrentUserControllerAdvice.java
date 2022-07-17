package by.academy.it.task13.controller.advice;

import by.academy.it.task13.controller.Constant;
import by.academy.it.task13.dto.user.UserNameDto;
import by.academy.it.task13.entity.User;
import by.academy.it.task13.exception.MailSenderException;
import by.academy.it.task13.mapper.impl.user.UserNameMapper;
import by.academy.it.task13.util.TelegramBot;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.security.core.Authentication;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;

@ControllerAdvice
@RequiredArgsConstructor
public class CurrentUserControllerAdvice {
    private static final Logger LOGGER = LogManager.getLogger(CurrentUserControllerAdvice.class);
    private final UserNameMapper mapper;
    private final TelegramBot bot;

    @ModelAttribute("currentUser")
    public UserNameDto getCurrentUser(Authentication authentication) {
        User user = null;
        if (authentication != null) {
            user = (User) authentication.getPrincipal();
            LOGGER.info(user.getUsername());
        }
        return mapper.toDto(user);
    }

    @ExceptionHandler(MailSenderException.class)
    public String mailSenderExceptionHandler(Model model, MailSenderException e) {
        LOGGER.info("mailSenderExceptionHandler: " + e.getMessage());
        bot.broadcastTextMessage(e.getMessage());
        model.addAttribute(Constant.MAIL_ERROR, Constant.TRUE);
        return Constant.REDIRECT_TO_ROOT;
    }
}
