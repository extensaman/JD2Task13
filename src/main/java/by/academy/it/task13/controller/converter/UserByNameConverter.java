package by.academy.it.task13.controller.converter;

import by.academy.it.task13.dto.user.UserDto;
import by.academy.it.task13.service.UserService;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserByNameConverter implements Converter<String, UserDto> {
    private static final Logger LOGGER = LogManager.getLogger(UserByNameConverter.class);

    private final UserService userService;

    @Override
    public UserDto convert(String source) {
        LOGGER.info("convert userName = " + source + " to UserDto");
        return userService.findByUsername(source).orElse(null);
    }
}
