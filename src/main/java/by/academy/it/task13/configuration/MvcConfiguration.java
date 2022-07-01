package by.academy.it.task13.configuration;

import by.academy.it.task13.AppSetting;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@RequiredArgsConstructor
public class MvcConfiguration implements WebMvcConfigurer {
    private static final Logger LOGGER = LogManager.getLogger(MvcConfiguration.class);

    private final AppSetting appSetting;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
/*        registry.addResourceHandler("/uploads/**")
                .addResourceLocations("file:/" + appSetting.getUploadPath() + "/");*/
        LOGGER.info("addResourceHandlers DONE");
    }
}
