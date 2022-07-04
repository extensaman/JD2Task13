package by.academy.it.task13.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.CascadeType;
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
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "certificate_order")
public class CertificateOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "order_status")
    @Enumerated(value = EnumType.ORDINAL)
    private OrderStatus orderStatus;

    @Column(length = 2000)
    private String details;

    @Column
    private String owner;

    @Column
    private LocalDate eventDate;

    @ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    @OnDelete(action = OnDeleteAction.NO_ACTION)
    private User user;

    @ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    @JoinColumn(name = "certificate_id")
    @OnDelete(action = OnDeleteAction.NO_ACTION)
    private Certificate certificate;

    // TODO may be need to change EAGER to LAZY
    @ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    @JoinColumn(name = "decoration_id")
    @OnDelete(action = OnDeleteAction.NO_ACTION)
    private CertificateDecoration certificateDecoration;

    @Column
    private LocalDateTime created;

    @Column
    private LocalDateTime updated;

    @PrePersist
    public void preCreated() {
        this.created = LocalDateTime.now();
    }

    @PreUpdate
    public void preUpdated() {
        this.updated = LocalDateTime.now();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CertificateOrder certificateOrder = (CertificateOrder) o;
        return Objects.equals(id, certificateOrder.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
