package by.academy.it.task13.controller.admin;

import by.academy.it.task13.dto.PhotoSessionDto;
import by.academy.it.task13.service.PhotoSessionService;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(AdminConstant.ADMIN_PHOTO_SESSION_MAPPING)
@RequiredArgsConstructor
public class AdminPhotoSessionController {
    private static final Logger LOGGER = LogManager.getLogger(AdminPhotoSessionController.class);

    private final PhotoSessionService photoSessionService;

    @GetMapping
    public String getPhotoSessionPage(Model model) {
        LOGGER.info("getPhotoSessionPage");
        model.addAttribute(AdminConstant.TITLE,
                AdminConstant.MENU_ADMIN_PHOTO_SESSION_MESSAGE);
        model.addAttribute(AdminConstant.PHOTO_SESSION_LIST, photoSessionService.findAll());
        return AdminConstant.ADMIN_PHOTO_SESSION_PAGE;
    }

    @PostMapping
    public String savePhotoSession(PhotoSessionDto photoSessionDto) {
        LOGGER.info("savePhotoSession");
        photoSessionService.save(photoSessionDto);
        return AdminConstant.REDIRECT_ADMIN_PHOTOSESSION;
    }

    @PostMapping(AdminConstant.DELETE_MAPPING)
    public String deletePhotoSession(PhotoSessionDto photoSessionDto) {
        LOGGER.info("deletePhotoSession");
        photoSessionService.delete(photoSessionDto);
        return AdminConstant.REDIRECT_ADMIN_PHOTOSESSION;
    }
}
