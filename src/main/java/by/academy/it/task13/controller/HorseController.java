package by.academy.it.task13.controller;

import by.academy.it.task13.controller.admin.AdminCertificateController;
import by.academy.it.task13.entity.Horse;
import by.academy.it.task13.service.HorseService;
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
@RequestMapping(Constant.HORSE_MAPPING)
@RequiredArgsConstructor
public class HorseController {
    private static final Logger LOGGER = LogManager.getLogger(AdminCertificateController.class);

    @Autowired
    private final HorseService horseService;

    @GetMapping
    public String getActiveHorsePage(Model model) {
        model.addAttribute(Constant.TITLE,
                Constant.TITLE_HORSE_MESSAGE);
        List<Horse> activeHorseList = horseService.findAll().stream()
                .filter(Horse::isActivity)
                .collect(Collectors.toList());
        model.addAttribute(Constant.ACTIVE_HORSE_LIST, activeHorseList);
        return Constant.HORSE_PAGE;
    }
}
