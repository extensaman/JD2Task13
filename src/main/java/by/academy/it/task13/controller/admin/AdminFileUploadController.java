package by.academy.it.task13.controller.admin;

import by.academy.it.task13.dto.AttachmentDto;
import by.academy.it.task13.service.AttachmentService;
import by.academy.it.task13.service.specification.filter.CertificateOrderFilter;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("admin/upload")
@RequiredArgsConstructor
public class AdminFileUploadController {
    private static final Logger LOGGER = LogManager.getLogger(AdminFileUploadController.class);

    private final AttachmentService attachmentService;

    @GetMapping
    public String getUploadForm(@RequestParam(value = "pageNumber", required = false, defaultValue = "1") int pageNumber,
                                @RequestParam(value = "size", required = false, defaultValue = "5") int size,
                                @RequestParam(value = "sortField", required = false, defaultValue = "id") String sortField,
                                @RequestParam(value = "sortDirection", required = false, defaultValue = "asc") String sortDirection,
                                Model model) {
        LOGGER.info("getUploadForm");
        model.addAttribute(AdminConstant.TITLE,
                AdminConstant.MENU_ADMIN_UPLOAD_MESSAGE);
/*
        model.addAttribute(AdminConstant.ATTACHMENT_LIST, attachmentService.findAll());
*/
        model.addAttribute(AdminConstant.SORT_FIELD, sortField);
        model.addAttribute(AdminConstant.SORT_DIRECTION, sortDirection);

        String reverseSortDirection = AdminConstant.ASC.equals(sortDirection) ? AdminConstant.DESC : AdminConstant.ASC;
        model.addAttribute(AdminConstant.REVERSE_SORT_DIRECTION, reverseSortDirection);
        model.addAttribute(AdminConstant.ATTACHMENT_PAGE,
                attachmentService.getExtendedPage(pageNumber, size, sortField, sortDirection));
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

    @PostMapping("/edit")
    public String editAttachmentName(@ModelAttribute AttachmentDto attachmentDto){
        // TODO Need add functionality for editing file name
        LOGGER.info("editAttachmentName => attachmentDto=" + attachmentDto);
        return "redirect:/admin/upload";
    }

    @PostMapping("/delete")
    public String deleteAttachmentName(@ModelAttribute AttachmentDto attachmentDto){
        // TODO Need add functionality for deleting file
        LOGGER.info("deleteAttachmentName => attachmentDto=" + attachmentDto);
        return "redirect:/admin/upload";
    }
}
