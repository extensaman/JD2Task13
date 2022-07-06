package by.academy.it.task13.service.specification.filter;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Objects;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CertificateOrderFilter {
    private String ownerFilter;
    private String detailsFilter;

    public boolean isApplying() {
        return ownerFilter != null || detailsFilter != null;
    }
}
