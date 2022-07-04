package by.academy.it.task13.dto.certificatedecoration;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class CertificateDecorationDto {
    private Long id;
    private boolean activity;
    private String name;
    private String description;
    private BigDecimal price;
    private boolean deliveryNecessity;
    private String photoFile;
}
