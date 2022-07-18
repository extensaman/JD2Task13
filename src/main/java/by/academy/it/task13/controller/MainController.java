package by.academy.it.task13.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@Controller
@RequestMapping(Constant.ROOT_PATH_MAPPING)
public class MainController {
    private static final Logger LOGGER = LogManager.getLogger(MainController.class);

    @GetMapping
    public String getMainPage(Model model,
                              @RequestParam(name = Constant.MAIL_ERROR, required = false) String mailErrorStatus) {
        model.addAttribute(Constant.TITLE, Constant.MENU_USER_MAIN_MESSAGE);
        LOGGER.info("mailErrorStatus = " + mailErrorStatus);
        Optional.ofNullable(mailErrorStatus).ifPresent(status ->
                model.addAttribute(Constant.MAIL_ERROR, status));
        return Constant.HOME_PAGE;
    }
}
