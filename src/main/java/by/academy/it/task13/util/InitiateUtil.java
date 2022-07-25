package by.academy.it.task13.util;

import by.academy.it.task13.AppSetting;
import by.academy.it.task13.entity.Attachment;
import by.academy.it.task13.service.AttachmentService;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class InitiateUtil implements CommandLineRunner {
    private static final Logger LOGGER = LogManager.getLogger(InitiateUtil.class);

    private final AttachmentService attachmentService;
    private final ImageFileList imageFileList;
    private final AppSetting appSetting;


    @Override
    public void run(String[] args) throws Exception {

        new File(appSetting.getUploadPath()).mkdirs();

        List<Attachment> attachments = imageFileList.getImageFileList().stream()
                .map(fileName ->
                        Attachment.builder()
                                .fileName(fileName)
                                .build())
                .collect(Collectors.toList());
        attachmentService.saveAll(attachments);

        LOGGER.info("Initialization is finished");
    }
}
