package by.academy.it.task13.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.util.Objects;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table (name = "ordering")
public class Ordering {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private boolean activity;

    @Column(nullable = false, length = 2000)
    private String description;

    @ManyToOne (cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    @JoinColumn(name = "client_id")
    private Client client;

    // TODO may be need to change EAGER to LAZY
    @ManyToOne (cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    @JoinColumn(name = "delivery_id")
    private CertificateDecoration certificateDecoration;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ordering ordering = (Ordering) o;
        return Objects.equals(id, ordering.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
