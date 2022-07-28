package by.academy.it.task13.repo;

import by.academy.it.task13.dto.CertificateOrderDto;
import by.academy.it.task13.entity.CertificateOrder;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CertificateOrderRepository
        extends PagingAndSortingRepository<CertificateOrder, Long>,
        JpaSpecificationExecutor<CertificateOrder> {
    @Query("select ord.id from CertificateOrder ord where ord.certificate.id = :id")
    List<Long> findCertificateOrderIdsByCertificateId(@Param("id") Long id);

    @Query("select ord.id from CertificateOrder ord where ord.certificateDecoration.id = :id")
    List<Long> findCertificateOrderIdsByCertificateDecorationId(@Param("id") Long id);

    @Query("select ord from CertificateOrder ord " +
            "join fetch ord.certificate " +
            "join fetch ord.certificateDecoration " +
            "join fetch ord.user " +
            "where ord.id = :id")
    Optional<CertificateOrder> findByIdFetchLazy(@Param("id") Long id);
}
