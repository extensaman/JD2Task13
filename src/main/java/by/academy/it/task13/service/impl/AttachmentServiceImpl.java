package by.academy.it.task13.service.impl;

import by.academy.it.task13.configuration.MvcConfiguration;
import by.academy.it.task13.dto.AttachmentDto;
import by.academy.it.task13.entity.Attachment;
import by.academy.it.task13.exception.AttachmentException;
import by.academy.it.task13.mapper.impl.AttachmentMapper;
import by.academy.it.task13.repo.AttachmentRepository;
import by.academy.it.task13.service.AttachmentService;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AttachmentServiceImpl implements AttachmentService {
    private static final Logger LOGGER = LogManager.getLogger(AttachmentServiceImpl.class);
    public static final String PUNCT_SYMBOL = "\\p{Punct}";
    public static final String DASH_SYMBOL = "-";
    public static final String SPACE_SYMBOL = " ";

    @Value("${upload.path}")
    public String uploadPath;

    private final AttachmentRepository repository;
    private final AttachmentMapper mapper;

    @Override
    public void addAttachment(MultipartFile file) {
        LOGGER.info("addAttachment");
        String fileName = new StringBuilder()
                .append(LocalDateTime.now().toString().replaceAll(PUNCT_SYMBOL, DASH_SYMBOL))
                .append('-')
                .append(file.getOriginalFilename().toLowerCase().replaceAll(SPACE_SYMBOL, DASH_SYMBOL))
                .toString();
        try {
            file.transferTo(new File(uploadPath + '/' + fileName));
        } catch (IOException | NullPointerException e) {
            throw new AttachmentException(e);
        }
        Attachment attachment = Attachment.builder()
                .fileName(fileName)
                .build();
        repository.save(attachment);
    }

    @Override
    public void addArrayOfAttachment(MultipartFile[] files) {
        LOGGER.info("addArrayOfAttachment");
        Arrays.stream(files).forEach(this::addAttachment);
    }

    @Override
    public void saveAll(List<Attachment> list) {
        LOGGER.info("saveAll");
        repository.saveAll(list);
    }

    @Override
    public void save(Attachment attachment) {
        LOGGER.info("save");
        repository.save(attachment);
    }

    @Override
    public Optional<AttachmentDto> findById(String id) {
        Optional<AttachmentDto> attachmentDto;
        try {
            attachmentDto = repository.findById(Long.parseLong(id)).map(mapper::toDto);
        } catch (NumberFormatException e) {
            return Optional.empty();
        }
        return attachmentDto;
    }

    @Override
    public List<AttachmentDto> findAll() {
        List<AttachmentDto> attachmentDtos = new ArrayList<>();
        for (Attachment attachment : repository.findAll()) {
            attachmentDtos.add(mapper.toDto(attachment));
        }
        return attachmentDtos;
    }
}
