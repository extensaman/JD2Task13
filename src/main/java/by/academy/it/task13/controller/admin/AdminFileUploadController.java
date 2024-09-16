package by.academy.it.task13.controller.admin;

import by.academy.it.task13.dto.AttachmentDto;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping(AdminConstant.ADMIN_UPLOAD_MAPPING)
@RequiredArgsConstructor
public class AdminFileUploadController {
    private static final Logger logger = LogManager.getLogger(AdminFileUploadController.class);

    private final AttachmentService attachmentService;

    @GetMapping
    public String getUploadForm(@RequestParam(value = "pageNumber", required = false, defaultValue = "1") int pageNumber,
                                @RequestParam(value = "size", required = false, defaultValue = "5") int size,
                                @RequestParam(value = "sortField", required = false, defaultValue = "id") String sortField,
                                @RequestParam(value = "sortDirection", required = false, defaultValue = "asc") String sortDirection,
                                Model model) {
        logger.info("getUploadForm");
        model.addAttribute(AdminConstant.TITLE,
                AdminConstant.MENU_ADMIN_UPLOAD_MESSAGE);

        model.addAttribute(AdminConstant.SORT_FIELD, sortField);
        model.addAttribute(AdminConstant.SORT_DIRECTION, sortDirection);

        String reverseSortDirection = AdminConstant.ASC.equals(sortDirection) ? AdminConstant.DESC : AdminConstant.ASC;
        model.addAttribute(AdminConstant.REVERSE_SORT_DIRECTION, reverseSortDirection);
        model.addAttribute(AdminConstant.ATTACHMENT_PAGE,
                attachmentService.getExtendedPage(pageNumber, size, sortField, sortDirection));
        return AdminConstant.ADMIN_FILE_UPLOAD;
    }

    @PostMapping
    public String postUploadForm(@ModelAttribute MultipartFile[] files) {
        logger.info("postUploadForm");
        if (files != null) {
            logger.info(files.length + " files received");
            attachmentService.addArrayOfAttachment(files);
        }
        return AdminConstant.REDIRECT_ADMIN_UPLOAD;
    }

    @PostMapping(AdminConstant.EDIT_MAPPING)
    public String editAttachmentName(@ModelAttribute AttachmentDto attachmentDto) {
        // TODO Need add functionality for editing file name
        logger.info("editAttachmentName => attachmentDto=" + attachmentDto);
        return AdminConstant.REDIRECT_ADMIN_UPLOAD;
    }

    @PostMapping(AdminConstant.DELETE_MAPPING)
    public String deleteAttachmentName(@ModelAttribute AttachmentDto attachmentDto) {
        // TODO Need add functionality for deleting file
        logger.info("deleteAttachmentName => attachmentDto=" + attachmentDto);
        return AdminConstant.REDIRECT_ADMIN_UPLOAD;
    }
}
