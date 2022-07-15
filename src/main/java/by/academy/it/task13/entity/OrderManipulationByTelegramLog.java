package by.academy.it.task13.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import java.time.LocalDateTime;
import java.util.Objects;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table
public class OrderManipulationByTelegramLog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "telegram_subscriber_id")
    private TelegramSubscriber telegramSubscriber;

    @Column
    @Enumerated(EnumType.STRING)
    private OrderType orderType;

    @Column
    private Long orderId;

    @Column
    @Enumerated(EnumType.STRING)
    private OrderStatus oldStatus;

    @Column
    private LocalDateTime created;

    @Column
    @Enumerated(EnumType.STRING)
    private OrderStatus newStatus;

    @Column
    private LocalDateTime updated;

    @PrePersist
    private void setCreateTime(){
        this.created = LocalDateTime.now();
    }

    @PreUpdate
    private void setUpdateTime(){
        this.updated = LocalDateTime.now();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderManipulationByTelegramLog that = (OrderManipulationByTelegramLog) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
