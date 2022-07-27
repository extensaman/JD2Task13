package by.academy.it.task13.service;

import by.academy.it.task13.dto.certificatedecoration.CertificateDecorationDto;
import by.academy.it.task13.dto.certificatedecoration.CertificateDecorationNameDto;
import by.academy.it.task13.entity.CertificateDecoration;

import java.util.List;
import java.util.Optional;

public interface CertificateDecorationService {
    Optional<CertificateDecorationDto> findById(String id);

    List<CertificateDecorationDto> findAll();

    List<CertificateDecorationNameDto> findAllCertificateDecorationNameDto();

    CertificateDecoration save(CertificateDecorationDto certificateDecorationDto);

    void saveAll(List<CertificateDecoration> list);

    void delete(CertificateDecorationDto certificateDecorationDto);

    List<CertificateDecorationDto> findAllActiveCertificateDecoration();
}
