package by.academy.it.task13.dto;

import by.academy.it.task13.entity.OrderStatus;
import by.academy.it.task13.entity.OrderType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class OrderManipulationByTelegramLogDto {
    private Long id;
    private TelegramSubscriberDto telegramSubscriberDto;
    private OrderType orderType;
    private Long orderId;
    private OrderStatus oldStatus;
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime created;
    private OrderStatus newStatus;
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime updated;
}
