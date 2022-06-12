package by.academy.it.task13;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
public class Jd2Task13Application implements WebMvcConfigurer {

	public static void main(String[] args) {
		SpringApplication.run(Jd2Task13Application.class, args);
	}

	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		registry.addViewController("/login");
	}
}
