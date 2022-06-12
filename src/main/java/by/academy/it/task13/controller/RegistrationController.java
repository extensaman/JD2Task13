package by.academy.it.task13.controller;

import by.academy.it.task13.repo.UserRepository;
import by.academy.it.task13.security.RegistrationForm;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/registration")
@RequiredArgsConstructor
public class RegistrationController {
    private static final Logger LOGGER = LogManager.getLogger(RegistrationController.class);

    private final UserRepository repository;
    private final PasswordEncoder encoder;

    @GetMapping
    public String getRegistrationPage(Model model){
        LOGGER.info("getRegistrationPage");
        model.addAttribute(Constant.TITLE,
                Constant.TITLE_REGISTRATION_MESSAGE);
        return "registration";
    }

    @PostMapping
    public String processRegistration(RegistrationForm form) {
        LOGGER.info("processRegistration");
        repository.save(form.toUser(encoder));
        return "redirect:/login";
    }
}
