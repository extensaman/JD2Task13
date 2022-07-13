package by.academy.it.task13.dto;

import by.academy.it.task13.entity.SubscriptionType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class SubscriptionDto {
    private Long id;
    private boolean activity;
    private SubscriptionType type;
    private String name;
    private String description;
    private Integer lessonCount;
    private Integer validity;
    private BigDecimal price;
    private String photoFile;
}
