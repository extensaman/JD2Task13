package by.academy.it.task13.service;

import by.academy.it.task13.dto.CertificateTypeDto;
import by.academy.it.task13.entity.CertificateType;

import java.util.List;
import java.util.Optional;

public interface CertificateTypeService {
    List<CertificateTypeDto> findAll();

    //Optional<CertificateType> findById(String id);

    CertificateType saveCertificateTypeAndUpdateAllCertificate(CertificateTypeDto certificateTypeDto);

    void saveAll(List<CertificateType> list);

    void delete(CertificateTypeDto certificateTypeDto);

    List<CertificateTypeDto> findAllActiveCertificateType();
}
