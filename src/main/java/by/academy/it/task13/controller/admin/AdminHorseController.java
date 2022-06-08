package by.academy.it.task13.controller.admin;

import by.academy.it.task13.entity.CertificateDecoration;
import by.academy.it.task13.entity.Horse;
import by.academy.it.task13.service.HorseService;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(AdminConstant.ADMIN_HORSE_MAPPING)
@RequiredArgsConstructor
public class AdminHorseController {
    private static final Logger LOGGER = LogManager.getLogger(AdminCertificateController.class);

    @Autowired
    private final HorseService horseService;

    @GetMapping
    public String getHorsePage(Model model) {
        model.addAttribute(AdminConstant.TITLE,
                AdminConstant.MENU_ADMIN_HORSE_MESSAGE);
        model.addAttribute(AdminConstant.HORSE_LIST, horseService.findAll());
        return AdminConstant.ADMIN_HORSE_PAGE;
    }

    @PostMapping
    public String saveHorse(@ModelAttribute Horse horse) {
        horseService.save(horse);
        return "redirect:/admin/horse";
    }

    @PostMapping(AdminConstant.DELETE_MAPPING)
    public String deleteHorse(@ModelAttribute Horse horse) {
        horseService.delete(horse);
        return "redirect:/admin/horse";
    }
}
