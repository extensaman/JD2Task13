package by.academy.it.task13.controller;

import by.academy.it.task13.entity.Ordering;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import java.util.Optional;

@Controller
@RequestMapping(Constant.ORDER_MAPPING)
@SessionAttributes("order")
@RequiredArgsConstructor
public class OrderController {
    private static final Logger LOGGER = LogManager.getLogger(OrderController.class);

    @PostMapping
    public String getOrderPage(@ModelAttribute Ordering order, Model model) {
        LOGGER.info("getOrderPage");
        LOGGER.info("DESCRIPTION in ORDER is " + order.getDescription());
        Optional.ofNullable(order.getUser())
                .ifPresent(user -> LOGGER.info("USER in ORDER is " + user.getUsername()));
        model.addAttribute(Constant.TITLE,
                Constant.TITLE_ORDER_MESSAGE);
        return Constant.COACH_PAGE;
    }
}
