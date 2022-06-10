package by.academy.it.task13.controller;

import by.academy.it.task13.controller.admin.AdminCertificateController;
import by.academy.it.task13.dto.CoachDto;
import by.academy.it.task13.entity.Coach;
import by.academy.it.task13.service.CoachService;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping(Constant.COACH_MAPPING)
@RequiredArgsConstructor
public class CoachController {
    private static final Logger LOGGER = LogManager.getLogger(AdminCertificateController.class);

    @Autowired
    private final CoachService coachService;

    @GetMapping
    public String getActiveCoachPage(Model model) {
        model.addAttribute(Constant.TITLE,
                Constant.TITLE_COACH_MESSAGE);
        List<CoachDto> activeCoachList = coachService.findAllActiveCoach();
        model.addAttribute(Constant.ACTIVE_COACH_LIST, activeCoachList);
        return Constant.COACH_PAGE;
    }
}
