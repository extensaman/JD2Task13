package by.academy.it.task13.repo;

import by.academy.it.task13.entity.CertificateOrder;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface CertificateOrderRepository extends PagingAndSortingRepository<CertificateOrder, Long> {
}
