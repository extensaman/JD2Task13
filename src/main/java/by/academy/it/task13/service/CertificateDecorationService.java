package by.academy.it.task13.service;

import by.academy.it.task13.dto.CertificateDecorationDto;
import by.academy.it.task13.entity.CertificateDecoration;
import by.academy.it.task13.entity.User;

import java.util.List;
import java.util.Optional;

public interface CertificateDecorationService {
    Optional<CertificateDecoration> findById(String id);

    List<CertificateDecorationDto> findAll();

    CertificateDecoration save(CertificateDecorationDto certificateDecorationDto);

    void saveAll(List<CertificateDecoration> list);

    void delete(CertificateDecorationDto certificateDecorationDto);

    List<CertificateDecorationDto> findAllActiveCertificateDecoration();
}
