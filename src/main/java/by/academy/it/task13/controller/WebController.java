package by.academy.it.task13.controller;

import by.academy.it.task13.service.CertificateTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WebController {

    @Autowired
    private final CertificateTypeService certificateTypeService;

    public WebController(CertificateTypeService certificateTypeService) {
        this.certificateTypeService = certificateTypeService;
    }


    @GetMapping(Constant.ROOT_PATH_MAPPING)
    public String getMainPage(Model model){
        model.addAttribute("list", certificateTypeService.findAll());
        model.addAttribute(Constant.TITLE,"menu_admin.main");
        return Constant.HOME_PAGE;
    }
}
