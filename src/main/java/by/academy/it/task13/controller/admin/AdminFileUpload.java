package by.academy.it.task13.controller.admin;

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

    @Value("${upload.path}")
    private String uploadPath;

    @GetMapping
    public String getUploadForm(Model model) {
        LOGGER.info("getUploadForm");
        model.addAttribute(AdminConstant.TITLE,
                AdminConstant.MENU_ADMIN_COACH_MESSAGE);
        return "admin/file_upload";
    }

    @PostMapping
    public String postUploadForm(Model model, @ModelAttribute MultipartFile file) throws IOException {
        if (file != null) {
            LOGGER.info(file.getOriginalFilename());
            File path = new File(uploadPath);
            if (!path.exists()) {
                path.mkdirs();
            }
            LOGGER.info(uploadPath + '/' + file.getOriginalFilename());
            file.transferTo(new File(uploadPath + '/' + file.getOriginalFilename()));
/*            Attachment.builder()
            .fileName(file.getOriginalFilename())*/
            model.addAttribute("fileName", file.getOriginalFilename());
        }
        model.addAttribute(AdminConstant.TITLE,
                AdminConstant.MENU_ADMIN_COACH_MESSAGE);
        return "/admin/file_view";
    }
}
