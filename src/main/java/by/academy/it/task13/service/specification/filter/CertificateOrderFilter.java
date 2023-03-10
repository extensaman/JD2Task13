package by.academy.it.task13.service.specification.filter;

import by.academy.it.task13.dto.certificate.CertificateNameDto;
import by.academy.it.task13.dto.user.UserNameDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.logging.log4j.util.Strings;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CertificateOrderFilter {
    private String ownerFilter;
    private String detailsFilter;
    private UserNameDto userFilter;
    private CertificateNameDto certificateFilter;

    public boolean isApplying() {
        return Strings.isNotBlank(ownerFilter) ||
                Strings.isNotBlank(detailsFilter) ||
                (userFilter != null && Strings.isNotBlank(userFilter.getUsername())) ||
                (certificateFilter != null && Strings.isNotBlank(certificateFilter.getName()));
    }
}
