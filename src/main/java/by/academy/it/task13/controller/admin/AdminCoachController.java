package by.academy.it.task13.controller.admin;

import by.academy.it.task13.dto.CoachDto;
import by.academy.it.task13.service.CoachService;
import by.academy.it.task13.util.ImageFileList;
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
    private static final Logger LOGGER = LogManager.getLogger(AdminCoachController.class);

    private final CoachService coachService;
    private final ImageFileList imageFileList;

    @GetMapping
    public String getCoachPage(Model model) {
        LOGGER.info("getCoachPage");
        model.addAttribute(AdminConstant.TITLE,
                AdminConstant.MENU_ADMIN_COACH_MESSAGE);
        model.addAttribute(AdminConstant.COACH_LIST, coachService.findAll());
        model.addAttribute(AdminConstant.IMAGE_FILE_LIST,
                imageFileList.getImageFileList());
        return AdminConstant.ADMIN_COACH_PAGE;
    }

    @PostMapping
    public String saveCoach(CoachDto coachDto) {
        LOGGER.info("saveCoach");
        coachService.save(coachDto);
        return AdminConstant.REDIRECT_ADMIN_COACH;
    }

    @PostMapping(AdminConstant.DELETE_MAPPING)
    public String deleteCoach(CoachDto coachDto) {
        LOGGER.info("deleteCoach");
        coachService.delete(coachDto);
        return AdminConstant.REDIRECT_ADMIN_COACH;
    }
}
