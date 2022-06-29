package by.academy.it.task13;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Getter
@Setter
public class AppSetting {
    @Value("${upload.path}")
    private String uploadPath;

}
