package by.academy.it.task13.service;

import by.academy.it.task13.dto.AttachmentDto;
import by.academy.it.task13.entity.Attachment;
import by.academy.it.task13.service.paging.ExtendedPage;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

public interface AttachmentService {
    void addAttachment(MultipartFile file);

    void addArrayOfAttachment(MultipartFile[] files);

    void saveAll(List<Attachment> list);

    Optional<AttachmentDto> findById(String id);

    List<AttachmentDto> findAll();

    void save(Attachment attachment);

    ExtendedPage<AttachmentDto> getExtendedPage(int pageNumber, int size, String sortField, String sortDirection);
}
