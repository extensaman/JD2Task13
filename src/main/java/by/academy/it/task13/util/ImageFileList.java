package by.academy.it.task13.util;

import by.academy.it.task13.configuration.MvcConfiguration;
import org.springframework.stereotype.Component;

import java.io.File;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Component
public class ImageFileList {
    public List<String> getImageFileList() {
        return Optional.ofNullable(new File(MvcConfiguration.uploadPath + "/").list())
                .map(Arrays::asList).orElse(Collections.EMPTY_LIST);
    }
}
