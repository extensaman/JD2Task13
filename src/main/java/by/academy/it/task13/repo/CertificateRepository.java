package by.academy.it.task13.repo;

import by.academy.it.task13.entity.Certificate;
import by.academy.it.task13.entity.CertificateType;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CertificateRepository extends PagingAndSortingRepository<Certificate, Long> {

    @Query("update Certificate c set c.certificateType.activity = ?2 where c.certificateType = ?1")
    void updateCertificateActivity(CertificateType certificateType, boolean certificateTypeActivity);

    @Query("from Certificate c where c.activity=true and c.certificateType.activity=true")
    List<Certificate> findCertificatesByActivityTrue();

    List<Certificate> findCertificatesByActivityTrueAndCertificateTypeId(Long id);
}
