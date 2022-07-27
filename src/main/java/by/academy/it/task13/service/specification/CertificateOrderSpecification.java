package by.academy.it.task13.service.specification;

import by.academy.it.task13.entity.CertificateOrder;
import by.academy.it.task13.entity.CertificateOrder_;
import by.academy.it.task13.service.CertificateService;
import by.academy.it.task13.service.UserService;
import by.academy.it.task13.service.specification.filter.CertificateOrderFilter;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class CertificateOrderSpecification {
    private static final Logger LOGGER = LogManager.getLogger(CertificateOrderSpecification.class);
    public static final char PERCENT_CHAR = '%';

    private final UserService userService;
    private final CertificateService certificateService;

    public Specification<CertificateOrder> getCertificateOrderSpecification(CertificateOrderFilter filter) {
        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();
            Optional.ofNullable(filter.getOwnerFilter())
                    .filter(StringUtils::isNotBlank)
                    .ifPresent(owner ->
                            predicates.add(criteriaBuilder.like(root.get(CertificateOrder_.OWNER), PERCENT_CHAR + owner + PERCENT_CHAR)));
            Optional.ofNullable(filter.getDetailsFilter())
                    .filter(StringUtils::isNotBlank)
                    .ifPresent(details ->
                            predicates.add(criteriaBuilder.like(root.get(CertificateOrder_.DETAILS), PERCENT_CHAR + details + PERCENT_CHAR)));
            Optional.ofNullable(filter.getUserFilter())
                    .filter(userNameDto -> StringUtils.isNotBlank(userNameDto.getUsername()))
                    .flatMap(userNameDto -> userService.findUserByUsername(userNameDto.getUsername()))
                    .ifPresent(user ->
                            predicates.add(criteriaBuilder.equal(root.get(CertificateOrder_.USER), user)));
            Optional.ofNullable(filter.getCertificateFilter())
                    .filter(certificateNameDto -> StringUtils.isNotBlank(certificateNameDto.getName()))
                    .flatMap(certificateNameDto -> certificateService.findById(certificateNameDto.getId()))
                    .ifPresent(certificate ->
                            predicates.add(criteriaBuilder.equal(root.get(CertificateOrder_.CERTIFICATE), certificate)));
            LOGGER.info("Predicate ARRAY size = " + predicates.size());
            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
    }
}
