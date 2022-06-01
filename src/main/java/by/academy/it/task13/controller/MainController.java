package by.academy.it.task13.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(Constant.ROOT_PATH_MAPPING)
public class MainController {

    @GetMapping
    public String getMainPage(Model model) {
        model.addAttribute(Constant.TITLE, Constant.MENU_USER_MAIN_MESSAGE);
        return Constant.HOME_PAGE;
    }
}
