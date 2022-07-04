package by.academy.it.task13.service.specification;

import by.academy.it.task13.entity.CertificateOrder;
import by.academy.it.task13.entity.CertificateOrder_;
import by.academy.it.task13.service.specification.filter.CertificateOrderFilter;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.data.jpa.domain.Specification;


import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CertificateOrderSpecification {
    private static final Logger LOGGER = LogManager.getLogger(CertificateOrderSpecification.class);
    public static final char PERCENT_CHAR = '%';

    public static Specification<CertificateOrder> getCertificateOrderSpecification(CertificateOrderFilter filter) {
        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();
            Optional.ofNullable(filter.getOwnerFilter())
                    .filter(StringUtils::isNotBlank)
                    .map(owner ->
                            predicates.add(criteriaBuilder.like(root.get(CertificateOrder_.OWNER), PERCENT_CHAR + owner + PERCENT_CHAR)));
            Optional.ofNullable(filter.getDetailsFilter())
                    .filter(StringUtils::isNotBlank)
                    .ifPresent(details ->
                            predicates.add(criteriaBuilder.like(root.get(CertificateOrder_.DETAILS), PERCENT_CHAR + details + PERCENT_CHAR)));
            LOGGER.info("Predicate ARRAY size = " + predicates.size());
            LOGGER.info("filter.getDetailsFilter() = " + filter.getDetailsFilter());
            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
    }
}
