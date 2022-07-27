package by.academy.it.task13.controller;

import by.academy.it.task13.service.PhotoSessionService;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(Constant.PHOTOSESSION_MAPPING)
@RequiredArgsConstructor
public class PhotosessionController {
    private static final Logger LOGGER = LogManager.getLogger(PhotosessionController.class);

    private final PhotoSessionService photoSessionService;

    @GetMapping
    public String getActivePhotoSessionPage(Model model) {
        LOGGER.info("getActivePhotoSessionPage");
        model.addAttribute(Constant.TITLE,
                Constant.TITLE_PHOTOSESSION_MESSAGE);
        model.addAttribute(Constant.ACTIVE_PHOTOSESSION_LIST,
                photoSessionService.findAllActivePhotoSession());
        return Constant.PHOTOSESSION_PAGE;
    }
}
