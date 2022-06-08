package by.academy.it.task13.controller.admin;

import by.academy.it.task13.entity.PhotoSession;
import by.academy.it.task13.service.PhotoSessionService;
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
@RequestMapping(AdminConstant.ADMIN_PHOTO_SESSION_MAPPING)
@RequiredArgsConstructor
public class AdminPhotoSessionController {
    private static final Logger LOGGER = LogManager.getLogger(AdminCertificateController.class);

    @Autowired
    private final PhotoSessionService photoSessionService;

    @GetMapping
    public String getPhotoSessionPage(Model model) {
        model.addAttribute(AdminConstant.TITLE,
                AdminConstant.MENU_ADMIN_PHOTO_SESSION_MESSAGE);
        model.addAttribute(AdminConstant.PHOTO_SESSION_LIST, photoSessionService.findAll());
        return AdminConstant.ADMIN_PHOTO_SESSION_PAGE;
    }

    @PostMapping
    public String savePhotoSession(@ModelAttribute PhotoSession photoSession) {
        photoSessionService.save(photoSession);
        return "redirect:/admin/photosession";
    }

    @PostMapping(AdminConstant.DELETE_MAPPING)
    public String deletePhotoSession(@ModelAttribute PhotoSession photoSession) {
        photoSessionService.delete(photoSession);
        return "redirect:/admin/photosession";
    }
}
