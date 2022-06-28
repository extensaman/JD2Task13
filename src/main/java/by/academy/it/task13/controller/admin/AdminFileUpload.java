package by.academy.it.task13.controller.admin;

import by.academy.it.task13.configuration.MvcConfiguration;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@Controller
@RequestMapping("admin/upload")
public class AdminFileUpload {
    private static final Logger LOGGER = LogManager.getLogger(AdminFileUpload.class);

    @GetMapping
    public String getUploadForm(Model model) {
        LOGGER.info("getUploadForm");
        model.addAttribute(AdminConstant.TITLE,
                AdminConstant.MENU_ADMIN_COACH_MESSAGE);
        return "admin/file_upload";
    }

    @PostMapping
    public String postUploadForm(Model model, @ModelAttribute MultipartFile[] files) throws IOException {
        if (files != null) {
            //LOGGER.info(files.getOriginalFilename());

            for(MultipartFile multipartFile : files){
                LOGGER.info(MvcConfiguration.uploadPath + '/' + multipartFile.getOriginalFilename());
                multipartFile.transferTo(new File(MvcConfiguration.uploadPath + '/' + multipartFile.getOriginalFilename()));
            }

/*            Attachment.builder()
            .fileName(file.getOriginalFilename())*/

            model.addAttribute("fileName", files.getOriginalFilename());
        }
        model.addAttribute(AdminConstant.TITLE,
                AdminConstant.MENU_ADMIN_COACH_MESSAGE);
        return "/admin/file_view";
    }
}
