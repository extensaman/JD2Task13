package by.academy.it.task13.controller.rest;

import by.academy.it.task13.controller.Constant;
import by.academy.it.task13.controller.admin.AdminHorseController;
import by.academy.it.task13.dto.SubscriptionDto;
import by.academy.it.task13.service.SubscriptionService;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin(origins = Constant.CROSS_ORIGIN)
@RequiredArgsConstructor
public class SubscriptionController {
    private static final Logger LOGGER = LogManager.getLogger(SubscriptionController.class);

    private final SubscriptionService subscriptionService;

    @GetMapping("/subscriptions")
    public List<SubscriptionDto> getAllSubscriptionDto() {
        LOGGER.info("getAllSubscriptionDto");
        return subscriptionService.findAll();
    }

    @PostMapping("/subscriptions")
    public void addSubscription(@RequestBody SubscriptionDto dto) {
        LOGGER.info("addSubscription");
        subscriptionService.save(dto);
    }
}
