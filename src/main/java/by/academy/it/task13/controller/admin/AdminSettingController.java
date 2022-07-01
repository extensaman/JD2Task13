package by.academy.it.task13.controller.admin;

import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(AdminConstant.ADMIN_SETTING_MAPPING)
@RequiredArgsConstructor
public class AdminSettingController {
    private static final Logger LOGGER = LogManager.getLogger(AdminSettingController.class);

    @GetMapping
    public String getSettingPage(Model model){
        LOGGER.info("getSettingPage");
        model.addAttribute(AdminConstant.TITLE,
                AdminConstant.MENU_ADMIN_SETTING_MESSAGE);
        return "admin/setting";
    }

}
