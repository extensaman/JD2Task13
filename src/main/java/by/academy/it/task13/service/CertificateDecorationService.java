package by.academy.it.task13.service;

import by.academy.it.task13.dto.CertificateDecorationDto;
import by.academy.it.task13.entity.CertificateDecoration;

import java.util.List;

public interface CertificateDecorationService {
    List<CertificateDecorationDto> findAll();

    CertificateDecoration save(CertificateDecorationDto certificateDecorationDto);

    void saveAll(List<CertificateDecoration> list);

    void delete(CertificateDecorationDto certificateDecorationDto);

    List<CertificateDecorationDto> findAllActiveCertificateDecoration();
}
