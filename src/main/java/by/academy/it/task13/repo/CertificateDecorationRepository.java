package by.academy.it.task13.repo;

import by.academy.it.task13.entity.CertificateDecoration;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CertificateDecorationRepository
        extends CrudRepository<CertificateDecoration, Long> {
    List<CertificateDecoration> findCertificateDecorationsByActivityTrue();
}
