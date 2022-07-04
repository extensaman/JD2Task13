package by.academy.it.task13.dto.certificate;

import by.academy.it.task13.entity.CertificateType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
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
}
