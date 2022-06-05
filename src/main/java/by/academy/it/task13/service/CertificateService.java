package by.academy.it.task13.service;

import by.academy.it.task13.entity.Certificate;
import by.academy.it.task13.entity.CertificateType;
import by.academy.it.task13.repo.CertificateRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CertificateService {
    @Autowired
    private final CertificateRepository repository;

    public List<Certificate> findAll() {
        return repository.findAll();
    }

    public void saveAll(List<Certificate> list) {
        repository.saveAll(list);
    }

    public void save(Certificate certificate) {
        repository.save(certificate);
    }

    public void delete(Certificate certificate) {
        repository.delete(certificate);
    }

}
