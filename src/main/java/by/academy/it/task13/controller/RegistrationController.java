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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequiredArgsConstructor
public class RegistrationController {
    private static final Logger LOGGER = LogManager.getLogger(RegistrationController.class);

    private final UserService userService;

    @GetMapping(Constant.REGISTRATION_MAPPING)
    public String getRegistrationPage(Model model) {
        LOGGER.info("getRegistrationPage");
        model.addAttribute(Constant.TITLE,
                Constant.TITLE_REGISTRATION_MESSAGE);
        model.addAttribute(Constant.USER_DTO,
                new UserDto());
        return Constant.REGISTRATION_PAGE;
    }

    @PostMapping(Constant.REGISTRATION_MAPPING)
    public String processRegistration(Model model, @Valid UserDto userDto, Errors errors) {
        LOGGER.info("processRegistration");
        model.addAttribute(Constant.TITLE,
                Constant.TITLE_REGISTRATION_MESSAGE);
        if(errors.hasErrors()){
            LOGGER.info("processRegistration :: errors.hasErrors()");
            return Constant.REGISTRATION_PAGE;
        }

        if(userService.addUser(userDto)){
            return "redirect:/login";
        }
        model.addAttribute("existenceOfUser","true");
        return Constant.REGISTRATION_PAGE;
    }

    @GetMapping(Constant.ACTIVATE_MAPPING)
    public String activateUser(Model model, @PathVariable String code){
        Boolean isUserActivated = userService.activateUser(code);
        model.addAttribute(Constant.IS_USER_ACTIVATED, isUserActivated);

        if(isUserActivated){
            model.addAttribute(Constant.TITLE,
                    Constant.TITLE_LOGIN_MESSAGE);
            return Constant.LOGIN_PAGE;
        }

        model.addAttribute(Constant.TITLE,
                Constant.TITLE_REGISTRATION_MESSAGE);
        model.addAttribute(Constant.USER_DTO,
                new UserDto());
        return Constant.REGISTRATION_PAGE;
    }
}
