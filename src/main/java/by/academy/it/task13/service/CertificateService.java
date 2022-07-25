package by.academy.it.task13.service;

import by.academy.it.task13.dto.certificate.CertificateDto;
import by.academy.it.task13.dto.certificate.CertificateNameDto;
import by.academy.it.task13.entity.Certificate;

import java.util.List;
import java.util.Optional;

public interface CertificateService {
    Optional<Certificate> findById(String id);

    Optional<Certificate> findById(Long id);

    Optional<CertificateDto> findCertificateDtoById(String id);

    Optional<CertificateNameDto> findCertificateNameDtoById(String id);

    List<CertificateDto> findAll();

    List<CertificateNameDto> findAllCertificateNameDto();

    void saveAll(List<Certificate> list);

    Certificate save(CertificateDto certificateDto);

    void delete(CertificateDto certificateDto);

    List<CertificateDto> findAllActiveCertificate();

    List<Certificate> findCertificatesByActivityTrueAndCertificateTypeId(String id);

    List<String> findCertificateNamesByCertificateTypeId(Long id);
}