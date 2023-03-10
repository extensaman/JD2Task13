package by.academy.it.task13.dto.certificate;

import by.academy.it.task13.entity.CertificateType;
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
public class CertificateDto {
    private Long id;
    private boolean activity;
    private CertificateType certificateType;
    private String name;
    private String description;
    private Integer horseCount;
    private Double duration;
    private BigDecimal price;
    private boolean photographerIncluded;
    private String photoFile;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CertificateDto that = (CertificateDto) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
