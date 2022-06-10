package by.academy.it.task13.service;

import by.academy.it.task13.entity.CertificateType;

import java.util.List;
import java.util.Optional;

public interface CertificateTypeService {
    List<CertificateType> findAll();

    Optional<CertificateType> findById(String id);

    void save(CertificateType type);

    void saveAll(List<CertificateType> list);

    void delete(CertificateType certificateType);
}
