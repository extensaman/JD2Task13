package by.academy.it.task13.controller.admin;

import by.academy.it.task13.controller.Constant;
import by.academy.it.task13.service.AttachmentService;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("admin/upload")
@RequiredArgsConstructor
public class AdminFileUploadController {
    private static final Logger LOGGER = LogManager.getLogger(AdminFileUploadController.class);

    private final AttachmentService attachmentService;

    @GetMapping
    public String getUploadForm(Model model) {
        LOGGER.info("getUploadForm");
        model.addAttribute(AdminConstant.TITLE,
                AdminConstant.MENU_ADMIN_UPLOAD_MESSAGE);
        model.addAttribute(AdminConstant.ATTACHMENT_LIST, attachmentService.findAll());
        return "admin/file_upload";
    }

    @PostMapping
    public String postUploadForm(@ModelAttribute MultipartFile[] files) {
        LOGGER.info("postUploadForm");
        if (files != null) {
            LOGGER.info(files.length + " files received");
            attachmentService.addArrayOfAttachment(files);
        }
        return "redirect:/admin/upload";
    }
}
