package by.academy.it.task13.controller.advice;

import by.academy.it.task13.dto.user.UserNameDto;
import by.academy.it.task13.entity.User;
import by.academy.it.task13.mapper.impl.user.UserNameMapper;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

@ControllerAdvice
@RequiredArgsConstructor
public class CurrentUserControllerAdvice {
    private static final Logger LOGGER = LogManager.getLogger(CurrentUserControllerAdvice.class);
    private final UserNameMapper mapper;

    @ModelAttribute("currentUser")
    public UserNameDto getCurrentUser(Authentication authentication) {
        User user = null;
        if (authentication != null) {
            user = (User) authentication.getPrincipal();
            LOGGER.info(user.getUsername());
        }
        return mapper.toDto(user);
    }
}
