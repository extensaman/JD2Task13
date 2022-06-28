package by.academy.it.task13.service.impl;

import by.academy.it.task13.entity.Attachment;
import by.academy.it.task13.service.AttachmentService;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AttachmentServiceImpl implements AttachmentService {
    private static final Logger LOGGER = LogManager.getLogger(AttachmentServiceImpl.class);

    @Override
    public Attachment addAttachment(MultipartFile file) throws IOException {
        return null;
    }

    @Override
    public List<Attachment> addArrayOfAttachment(MultipartFile[] files) throws IOException {
        return Arrays.stream(files).map(AttachmentService::addAttachment).collect(Collectors.toList());
    }

    @Override
    public Attachment findById(Long id) {
        return null;
    }
}
