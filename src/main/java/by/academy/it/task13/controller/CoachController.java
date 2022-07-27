package by.academy.it.task13.controller;

import by.academy.it.task13.service.CoachService;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(Constant.COACH_MAPPING)
@RequiredArgsConstructor
public class CoachController {
    private static final Logger LOGGER = LogManager.getLogger(CoachController.class);

    private final CoachService coachService;

    @GetMapping
    public String getActiveCoachPage(Model model) {
        LOGGER.info("getActiveCoachPage");
        model.addAttribute(Constant.TITLE,
                Constant.TITLE_COACH_MESSAGE);
        model.addAttribute(Constant.ACTIVE_COACH_LIST, coachService.findAllActiveCoach());
        return Constant.COACH_PAGE;
    }
}
