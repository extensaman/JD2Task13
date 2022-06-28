package by.academy.it.task13.service;

import by.academy.it.task13.entity.Attachment;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface AttachmentService {
    Attachment addAttachment(MultipartFile file) throws IOException;
    List<Attachment> addArrayOfAttachment(MultipartFile[] files) throws IOException;
    Attachment findById(Long id);
}
