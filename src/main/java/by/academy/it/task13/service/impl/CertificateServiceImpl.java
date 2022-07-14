package by.academy.it.task13.service.impl;

import by.academy.it.task13.dto.certificate.CertificateDto;
import by.academy.it.task13.dto.certificate.CertificateNameDto;
import by.academy.it.task13.entity.Certificate;
import by.academy.it.task13.mapper.Mapper;
import by.academy.it.task13.repo.CertificateRepository;
import by.academy.it.task13.service.CertificateService;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CertificateServiceImpl implements CertificateService {
    private static final Logger LOGGER = LogManager.getLogger(CertificateServiceImpl.class);

    private final CertificateRepository repository;
    private final Mapper<Certificate, CertificateDto> mapper;
    private final Mapper<Certificate, CertificateNameDto> mapperName;

    @Override
    public Optional<CertificateDto> findById(String id) {
        LOGGER.info("findById");
        Optional<Certificate> certificate;
        try {
            certificate = repository.findById(Long.parseLong(id));
        } catch (NumberFormatException e) {
            certificate = Optional.empty();
        }
        return certificate.map(mapper::toDto);
    }

    @Override
    public List<CertificateDto> findAll() {
        LOGGER.info("findAll");
        List<CertificateDto> certificateDtos = new ArrayList<>();
        for (Certificate certificate : repository.findAll()) {
            certificateDtos.add(mapper.toDto(certificate));
        }
        return certificateDtos;
    }

    @Override
    public List<CertificateNameDto> findAllCertificateNameDto() {
        LOGGER.info("findAllCertificateNameDto");
        List<CertificateNameDto> certificateNameDtos = new ArrayList<>();
        for (Certificate certificate : repository.findAll()) {
            certificateNameDtos.add(mapperName.toDto(certificate));
        }
        return certificateNameDtos;
    }

    @Override
    public List<CertificateDto> findAllActiveCertificate() {
        LOGGER.info("findAllActiveCertificate");
        return repository.findCertificatesByActivityTrue().stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<Certificate> findCertificatesByActivityTrueAndCertificateTypeId(String id) {
        LOGGER.info("findCertificatesByActivityTrueAndCertificateTypeId");
        long identifier;
        try {
            identifier = Long.parseLong(id);
        } catch (NumberFormatException e) {
            return null;
        }
        return repository.findCertificatesByActivityTrueAndCertificateTypeId(identifier);
    }

    @Override
    @Transactional
    public Certificate save(CertificateDto certificateDto) {
        LOGGER.info("save");
        Certificate certificate = mapper.toEntity(certificateDto);
        if (!certificate.getCertificateType().isActivity()) {
            certificate.setActivity(false);
        }
        return repository.save(certificate);
    }

    @Override
    @Transactional
    public void saveAll(List<Certificate> list) {
        LOGGER.info("saveAll");
        list.forEach(certificate -> {
            if (!certificate.getCertificateType().isActivity()) {
                certificate.setActivity(false);
            }
        });
        repository.saveAll(list);
    }

    @Override
    @Transactional
    public void delete(CertificateDto certificateDto) {
        LOGGER.info("delete");
        repository.delete(mapper.toEntity(certificateDto));
    }

    @Override
    public List<String> findCertificateNamesByCertificateTypeId(Long id) {
        LOGGER.info("findCertificateNamesByCertificateTypeId");
        return repository.findCertificateNamesByCertificateTypeId(id);
    }
}
