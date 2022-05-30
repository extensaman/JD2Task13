package by.academy.it.task13.repo;

import by.academy.it.task13.entity.CertificateType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CertificateTypeRepository
        extends JpaRepository<CertificateType, Long> {
    List<CertificateType> findAll();
}
