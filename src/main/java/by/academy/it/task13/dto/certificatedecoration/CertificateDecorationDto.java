package by.academy.it.task13.dto.certificatedecoration;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;
import java.util.Objects;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
public class CertificateDecorationDto {
    private Long id;
    private boolean activity;
    private String name;
    private String description;
    private BigDecimal price;
    private boolean deliveryNecessity;
    private String photoFile;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CertificateDecorationDto that = (CertificateDecorationDto) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
