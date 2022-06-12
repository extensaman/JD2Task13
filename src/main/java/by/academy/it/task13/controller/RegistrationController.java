package by.academy.it.task13.controller;

import by.academy.it.task13.dto.UserDto;
import by.academy.it.task13.service.UserService;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/registration")
@RequiredArgsConstructor
public class RegistrationController {
    private static final Logger LOGGER = LogManager.getLogger(RegistrationController.class);

    private final UserService userService;

    @GetMapping
    public String getRegistrationPage(Model model) {
        LOGGER.info("getRegistrationPage");
        model.addAttribute(Constant.TITLE,
                Constant.TITLE_REGISTRATION_MESSAGE);
        return "registration";
    }

    @PostMapping
    public String processRegistration(UserDto userDto) {
        LOGGER.info("processRegistration");
        userService.save(userDto);
        return "redirect:/login";
    }
}
