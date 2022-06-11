package by.academy.it.task13.repo;

import by.academy.it.task13.entity.Certificate;
import by.academy.it.task13.entity.CertificateType;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface CertificateRepository extends PagingAndSortingRepository<Certificate, Long> {
    @Modifying
    @Transactional
    @Query("update Certificate c set c.activity = :#{#type.activity} where c.certificateType = :type")
    void updateCertificateActivity(@Param("type") CertificateType certificateType);

    @Query("from Certificate c where c.activity=true and c.certificateType.activity=true")
    List<Certificate> findCertificatesByActivityTrue();

    List<Certificate> findCertificatesByActivityTrueAndCertificateTypeId(Long id);
}
