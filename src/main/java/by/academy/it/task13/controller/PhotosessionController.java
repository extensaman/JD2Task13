package by.academy.it.task13.controller;

import by.academy.it.task13.controller.admin.AdminCertificateController;
import by.academy.it.task13.controller.admin.AdminConstant;
import by.academy.it.task13.entity.Certificate;
import by.academy.it.task13.entity.PhotoSession;
import by.academy.it.task13.service.PhotoSessionService;
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
@RequestMapping(Constant.PHOTOSESSION_MAPPING)
@RequiredArgsConstructor
public class PhotosessionController {
    private static final Logger LOGGER = LogManager.getLogger(AdminCertificateController.class);

    @Autowired
    private final PhotoSessionService photoSessionService;

    @GetMapping
    public String getActivePhotoSessionPage(Model model) {
        model.addAttribute(Constant.TITLE,
                Constant.TITLE_PHOTOSESSION_MESSAGE);
        List<PhotoSession> activePhotoSessionList = photoSessionService.findAll().stream()
                .filter(PhotoSession::isActivity)
                .collect(Collectors.toList());
        model.addAttribute(Constant.ACTIVE_PHOTOSESSION_LIST, activePhotoSessionList);
        return Constant.PHOTOSESSION_PAGE;
    }
}
