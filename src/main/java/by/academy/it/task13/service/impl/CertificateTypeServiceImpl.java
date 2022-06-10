package by.academy.it.task13.service.impl;

import by.academy.it.task13.dto.CertificateTypeDto;
import by.academy.it.task13.entity.CertificateType;
import by.academy.it.task13.mapper.Mapper;
import by.academy.it.task13.repo.CertificateRepository;
import by.academy.it.task13.repo.CertificateTypeRepository;
import by.academy.it.task13.service.CertificateTypeService;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CertificateTypeServiceImpl implements CertificateTypeService {
    private static final Logger LOGGER = LogManager.getLogger(CertificateTypeServiceImpl.class);

    private final CertificateTypeRepository certificateTypeRepository;
    private final CertificateRepository certificateRepository;
    private final Mapper<CertificateType, CertificateTypeDto> mapper;

    @Override
    public List<CertificateTypeDto> findAll() {
        LOGGER.info("findAll");
        List<CertificateTypeDto> certificateTypeDtos = new ArrayList<>();
        for (CertificateType certificateType : certificateTypeRepository.findAll()) {
            certificateTypeDtos.add(mapper.toDto(certificateType));
        }
        return certificateTypeDtos;
    }

    @Override
    public List<CertificateTypeDto> findAllActiveCertificateType() {
        LOGGER.info("findAllActiveCertificateType");
        return certificateTypeRepository.findCertificateTypesByActivityTrue().stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public CertificateType saveCertificateTypeAndUpdateAllCertificate(CertificateTypeDto certificateTypeDto) {
        LOGGER.info("saveCertificateTypeAndUpdateAllCertificate");
        CertificateType certificateType = certificateTypeRepository.save(mapper.toEntity(certificateTypeDto));
        certificateRepository.updateCertificateActivity(certificateType, certificateType.isActivity());
        /*certificateRepository.findAll().stream()
                .filter(certificate -> certificate.getCertificateType().equals(certificateType))
                .peek(certificate -> certificate.setActivity(certificateType.isActivity()))
                .forEach(certificateRepository::save);*/
        return certificateType;
    }

    @Override
    public void saveAll(List<CertificateType> list) {
        LOGGER.info("saveAll");
        certificateTypeRepository.saveAll(list);
    }

    @Override
    public void delete(CertificateTypeDto certificateTypeDto) {
        LOGGER.info("delete");
        certificateTypeRepository.delete(mapper.toEntity(certificateTypeDto));
    }


/*    @Override
    public Optional<CertificateType> findById(String id) {
        Optional<CertificateType> result;
        try {
            result = certificateTypeRepository.findById(Long.parseLong(id));
        } catch (NumberFormatException e) {
            result = Optional.empty();
        }
        return result;
    }*/
}
