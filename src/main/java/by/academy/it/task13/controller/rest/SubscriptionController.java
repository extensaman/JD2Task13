package by.academy.it.task13.controller.rest;

import by.academy.it.task13.controller.Constant;
import by.academy.it.task13.dto.SubscriptionDto;
import by.academy.it.task13.service.SubscriptionService;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(Constant.SUBSCRIPTIONS_MAPPING)
@CrossOrigin(origins = Constant.CROSS_ORIGIN)
@RequiredArgsConstructor
public class SubscriptionController {
    private static final Logger logger = LogManager.getLogger(SubscriptionController.class);

    private final SubscriptionService subscriptionService;

    @GetMapping(produces = Constant.APPLICATION_JSON)
    public List<SubscriptionDto> getAllSubscriptionDto() {
        logger.info("getAllSubscriptionDto");
        return subscriptionService.findAll();
    }

    @GetMapping(path = Constant.ID_MAPPING, produces = Constant.APPLICATION_JSON)
    public ResponseEntity<SubscriptionDto> getSubscriptionDtoById(@PathVariable Long id) {
        logger.info("getSubscriptionById");
        return subscriptionService.findById(id).map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping(consumes = Constant.APPLICATION_JSON)
    @ResponseStatus(HttpStatus.CREATED)
    public void addSubscription(@RequestBody SubscriptionDto dto) {
        logger.info("addSubscription");
        subscriptionService.save(dto);
    }
}
