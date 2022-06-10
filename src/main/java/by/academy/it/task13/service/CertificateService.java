package by.academy.it.task13.service;

import by.academy.it.task13.dto.CertificateDto;
import by.academy.it.task13.entity.Certificate;

import java.util.List;

public interface CertificateService {
    List<CertificateDto> findAll();

    void saveAll(List<Certificate> list);

    Certificate save(CertificateDto certificateDto);

    void delete(CertificateDto certificateDto);

    List<CertificateDto> findAllActiveCertificate();

    List<Certificate> findCertificatesByActivityTrueAndCertificateTypeId(String id);
}
