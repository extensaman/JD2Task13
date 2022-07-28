package by.academy.it.task13.controller.admin;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(AdminConstant.ADMIN_MAIN_MAPPING)
public class AdminController {
    private static final Logger logger = LogManager.getLogger(AdminController.class);

    @GetMapping
    public String getMainAdminPage(Model model) {
        logger.info("getMainAdminPage");
        model.addAttribute(AdminConstant.TITLE,
                AdminConstant.MENU_ADMIN_MAIN_MESSAGE);
        return AdminConstant.ADMIN_MAIN_PAGE;
    }


}
