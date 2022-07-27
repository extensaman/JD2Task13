package by.academy.it.task13.repo;

import by.academy.it.task13.entity.CertificateType;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CertificateTypeRepository
        extends CrudRepository<CertificateType, Long> {
    List<CertificateType> findCertificateTypesByActivityTrue();

}
