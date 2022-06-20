package by.academy.it.task13.controller;

import by.academy.it.task13.dto.user.UserDto;
import by.academy.it.task13.service.UserService;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

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
        model.addAttribute("userDto",
                new UserDto());
        return Constant.REGISTRATION_PAGE;
    }

    @PostMapping
    public String processRegistration(Model model, @Valid UserDto userDto, Errors errors) {
        LOGGER.info("processRegistration");
        model.addAttribute(Constant.TITLE,
                Constant.TITLE_REGISTRATION_MESSAGE);
        if(errors.hasErrors()){
            LOGGER.info("processRegistration :: errors.hasErrors()");
            return Constant.REGISTRATION_PAGE;
        }

        if(userService.save(userDto)){
            return "redirect:/login";
        }
        model.addAttribute("existenceOfUser","true");
        return Constant.REGISTRATION_PAGE;
    }
}
