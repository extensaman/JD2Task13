package by.academy.it.task13.service.impl;

import by.academy.it.task13.entity.Certificate;
import by.academy.it.task13.repo.CertificateRepository;
import by.academy.it.task13.service.CertificateService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CertificateServiceImpl implements CertificateService {
    @Autowired
    private final CertificateRepository repository;

    @Override
    public List<Certificate> findAll() {
        return repository.findAll();
    }

    @Override
    public void saveAll(List<Certificate> list) {
        repository.saveAll(list);
    }

    @Override
    public void save(Certificate certificate) {
        repository.save(certificate);
    }

    @Override
    public void delete(Certificate certificate) {
        repository.delete(certificate);
    }

}
