package by.academy.it.task13.service;

import by.academy.it.task13.entity.CertificateDecoration;

import java.util.List;

public interface CertificateDecorationService {
    List<CertificateDecoration> findAll();

    void saveAll(List<CertificateDecoration> list);

    void save(CertificateDecoration certificateDecoration);

    void delete(CertificateDecoration certificateDecoration);
}
