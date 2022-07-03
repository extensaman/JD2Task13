package by.academy.it.task13.service.specification.filter;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CertificateOrderFilter {
    private String ownerFilter;
    private String detailsFilter;
}
