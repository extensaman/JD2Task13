package by.academy.it.task13.service.impl;

import by.academy.it.task13.AppSetting;
import by.academy.it.task13.dto.AttachmentDto;
import by.academy.it.task13.entity.Attachment;
import by.academy.it.task13.exception.AttachmentException;
import by.academy.it.task13.mapper.impl.AttachmentMapper;
import by.academy.it.task13.repo.AttachmentRepository;
import by.academy.it.task13.service.AttachmentService;
import by.academy.it.task13.service.paging.ExtendedPage;
import by.academy.it.task13.service.paging.PaginationControl;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
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

    private final AttachmentRepository repository;
    private final AttachmentMapper mapper;
    private final AppSetting appSetting;

    @Override
    @Transactional
    public void addAttachment(MultipartFile multipartFile) {
        LOGGER.info("addAttachment");
        Optional.ofNullable(multipartFile)
                .ifPresent(file -> {
                    String fileName;
                    try {
                        fileName = new StringBuilder()
                                .append(LocalDateTime.now().toString().replaceAll(PUNCT_SYMBOL, DASH_SYMBOL))
                                .append('-')
                                .append(
                                        URLEncoder.encode(
                                                file.getOriginalFilename()
                                                        .toLowerCase()
                                                        .replaceAll(SPACE_SYMBOL, DASH_SYMBOL),
                                                StandardCharsets.UTF_8
                                        )
                                )
                                .toString();

                        file.transferTo(new File(appSetting.getUploadPath() + '/' + fileName));
                    } catch (IOException | NullPointerException e) {
                        throw new AttachmentException(e);
                    }
                    Attachment attachment = Attachment.builder()
                            .fileName(fileName)
                            .build();
                    repository.save(attachment);
                });
    }

    @Override
    public void addArrayOfAttachment(MultipartFile[] files) {
        LOGGER.info("addArrayOfAttachment");
        Arrays.stream(files).forEach(this::addAttachment);
    }

    @Override
    @Transactional
    public void saveAll(List<Attachment> list) {
        LOGGER.info("saveAll");
        repository.saveAll(list);
    }

    @Override
    @Transactional
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

    @Override
    public ExtendedPage<AttachmentDto> getExtendedPage(int pageNumber, int size, String sortField, String sortDirection) {
        LOGGER.info("getExtendedPage");
        Sort sort = Sort.by(sortField);
        sort = Constant.ASC.equalsIgnoreCase(sortDirection) ? sort.ascending() : sort.descending();
        PageRequest request = PageRequest.of(pageNumber - Constant.ONE, size, sort);
        Page<AttachmentDto> postPage = repository.findAll(request).map(mapper::toDto);
        return new ExtendedPage<>(postPage, PaginationControl.of(postPage.getTotalPages(), pageNumber, size));
    }
}
