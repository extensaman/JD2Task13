package by.academy.it.task13.controller.admin;

import by.academy.it.task13.dto.HorseDto;
import by.academy.it.task13.service.HorseService;
import by.academy.it.task13.util.ImageFileList;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
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
    private static final Logger LOGGER = LogManager.getLogger(AdminHorseController.class);

    private final HorseService horseService;
    private final ImageFileList imageFileList;

    @GetMapping
    public String getHorsePage(Model model) {
        LOGGER.info("getHorsePage");
        model.addAttribute(AdminConstant.TITLE,
                AdminConstant.MENU_ADMIN_HORSE_MESSAGE);
        model.addAttribute(AdminConstant.HORSE_LIST, horseService.findAll());
        model.addAttribute(AdminConstant.IMAGE_FILE_LIST,
                imageFileList.getImageFileList());
        return AdminConstant.ADMIN_HORSE_PAGE;
    }

    @PostMapping
    public String saveHorse(@ModelAttribute HorseDto horseDto) {
        LOGGER.info("saveHorse");
        horseService.save(horseDto);
        return "redirect:/admin/horse";
    }

    @PostMapping(AdminConstant.DELETE_MAPPING)
    public String deleteHorse(@ModelAttribute HorseDto horseDto) {
        LOGGER.info("deleteHorse");
        horseService.delete(horseDto);
        return "redirect:/admin/horse";
    }
}
