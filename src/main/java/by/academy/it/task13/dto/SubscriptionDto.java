package by.academy.it.task13.dto;

import by.academy.it.task13.entity.SubscriptionType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SubscriptionDto that = (SubscriptionDto) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
