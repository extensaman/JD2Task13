package by.academy.it.task13.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(Constant.LOGIN_MAPPING)
public class LoginController {
    private static final Logger logger = LogManager.getLogger(RegistrationController.class);


    @GetMapping
    public String getLoginPage(Model model) {
        logger.info("getLoginPage");
        model.addAttribute(Constant.TITLE,
                Constant.TITLE_LOGIN_MESSAGE);
        return Constant.LOGIN_PAGE;
    }
}
