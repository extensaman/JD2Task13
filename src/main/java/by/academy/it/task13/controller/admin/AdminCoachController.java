package by.academy.it.task13.controller.admin;

import by.academy.it.task13.entity.Coach;
import by.academy.it.task13.service.CoachService;
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
@RequestMapping(AdminConstant.ADMIN_COACH_MAPPING)
@RequiredArgsConstructor
public class AdminCoachController {
    private static final Logger LOGGER = LogManager.getLogger(AdminCertificateController.class);

    @Autowired
    private final CoachService coachService;

    @GetMapping
    public String getCoachPage(Model model) {
        model.addAttribute(AdminConstant.TITLE,
                AdminConstant.MENU_ADMIN_COACH_MESSAGE);
        model.addAttribute(AdminConstant.COACH_LIST, coachService.findAll());
        return AdminConstant.ADMIN_COACH_PAGE;
    }

    @PostMapping
    public String saveCoach(@ModelAttribute Coach coach) {
        coachService.save(coach);
        return "redirect:/admin/coach";
    }

    @PostMapping(AdminConstant.DELETE_MAPPING)
    public String deleteCoach(@ModelAttribute Coach coach) {
        coachService.delete(coach);
        return "redirect:/admin/coach";
    }
}
