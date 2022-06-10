package by.academy.it.task13.util;

import org.springframework.stereotype.Component;

import java.io.File;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Component
public class ImageFileList {
    public static final String SRC_MAIN_RESOURCES_STATIC_IMG = "./src/main/resources/static/img";
    private final List<String> imageFileList;

    public ImageFileList() {
        imageFileList = Optional.ofNullable(new File(SRC_MAIN_RESOURCES_STATIC_IMG).list())
                .map(Arrays::asList).orElse(null);
    }

    public List<String> getImageFileList() {
        return imageFileList;
    }
}
