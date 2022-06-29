package by.academy.it.task13.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.File;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Component
public class ImageFileList {
    @Value("${upload.path}")
    public String uploadPath;

    public List<String> getImageFileList() {
        return Optional.ofNullable(new File(uploadPath + "/").list())
                .map(Arrays::asList).orElse(Collections.EMPTY_LIST);
    }
}
