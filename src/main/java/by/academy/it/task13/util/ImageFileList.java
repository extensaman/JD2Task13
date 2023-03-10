package by.academy.it.task13.util;

import by.academy.it.task13.AppSetting;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.io.File;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class ImageFileList {
    private final AppSetting appSetting;

    public List<String> getImageFileList() {
        return Optional.ofNullable(new File(appSetting.getUploadPath() + "/").list())
                .map(Arrays::asList).orElse(Collections.EMPTY_LIST);
    }
}
