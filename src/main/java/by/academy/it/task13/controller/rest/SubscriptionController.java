package by.academy.it.task13.controller.rest;

import by.academy.it.task13.controller.Constant;
import by.academy.it.task13.dto.SubscriptionDto;
import by.academy.it.task13.service.SubscriptionService;
import lombok.RequiredArgsConstructor;
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
    private final SubscriptionService subscriptionService;

    @GetMapping("/subscriptions")
    public List<SubscriptionDto> getAllSubscriptionDto() {
        return subscriptionService.findAll();
    }

    @PostMapping("/subscriptions")
    public void addSubscription(@RequestBody SubscriptionDto dto) {
        subscriptionService.save(dto);
    }
}
