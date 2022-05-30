package by.academy.it.task13.repo;

import by.academy.it.task13.entity.Certificate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CertificateRepository extends JpaRepository<Certificate, Long> {
    //List<Certificate> findAll();
}
