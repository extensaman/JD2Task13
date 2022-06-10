package by.academy.it.task13.service;

import by.academy.it.task13.entity.Certificate;

import java.util.List;

public interface CertificateService {
    List<Certificate> findAll();

    void saveAll(List<Certificate> list);

    void save(Certificate certificate);

    void delete(Certificate certificate);
}
