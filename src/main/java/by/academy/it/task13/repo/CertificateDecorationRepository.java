package by.academy.it.task13.repo;

import by.academy.it.task13.entity.CertificateDecoration;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CertificateDecorationRepository
        extends CrudRepository<CertificateDecoration, Long> {
    List<CertificateDecoration> findCertificateDecorationsByActivityTrue();
}
