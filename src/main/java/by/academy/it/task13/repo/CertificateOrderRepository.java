package by.academy.it.task13.repo;

import by.academy.it.task13.entity.CertificateOrder;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CertificateOrderRepository
            extends PagingAndSortingRepository<CertificateOrder, Long>,
                    JpaSpecificationExecutor<CertificateOrder> {
    List<CertificateOrder> findCertificateOrdersByCertificateId(Long id);
    List<CertificateOrder> findCertificateOrdersByCertificateDecorationId(Long id);
}
