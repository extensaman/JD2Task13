package by.academy.it.task13.controller.advice;

import by.academy.it.task13.entity.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

@ControllerAdvice
public class CurrentUserControllerAdvice {
    private static final Logger LOGGER = LogManager.getLogger(CurrentUserControllerAdvice.class);

    @ModelAttribute("currentUser")
    public User getCurrentUser(Authentication authentication) {
        User user = null;
        if (authentication != null) {
            user = (User) authentication.getPrincipal();
            LOGGER.info(user.getUsername());
        }
        return user;
    }
}
