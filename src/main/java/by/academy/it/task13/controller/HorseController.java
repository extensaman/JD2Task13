package by.academy.it.task13.controller;

import by.academy.it.task13.service.HorseService;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(Constant.HORSE_MAPPING)
@RequiredArgsConstructor
public class HorseController {
    private static final Logger LOGGER = LogManager.getLogger(HorseController.class);

    private final HorseService horseService;

    @GetMapping
    public String getActiveHorsePage(Model model) {
        LOGGER.info("getActiveHorsePage");
        model.addAttribute(Constant.TITLE,
                Constant.TITLE_HORSE_MESSAGE);
        model.addAttribute(Constant.ACTIVE_HORSE_LIST, horseService.findAllActiveHorse());
        return Constant.HORSE_PAGE;
    }
}
