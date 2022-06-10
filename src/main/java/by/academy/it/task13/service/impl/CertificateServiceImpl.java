package by.academy.it.task13.service.impl;

import by.academy.it.task13.dto.CertificateDto;
import by.academy.it.task13.entity.Certificate;
import by.academy.it.task13.mapper.Mapper;
import by.academy.it.task13.repo.CertificateRepository;
import by.academy.it.task13.service.CertificateService;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CertificateServiceImpl implements CertificateService {
    private static final Logger LOGGER = LogManager.getLogger(CertificateServiceImpl.class);

    private final CertificateRepository repository;
    private final Mapper<Certificate, CertificateDto> mapper;

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
    public List<CertificateDto> findAllActiveCertificate() {
        LOGGER.info("findAllActiveCertificate");
        return repository.findCertificatesByActivityTrue().stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<Certificate> findCertificatesByActivityTrueAndCertificateTypeId(String id) {
        long identifier;
        try{
            identifier = Long.parseLong(id);
        } catch (NumberFormatException e){
            return null;
        }
        return repository.findCertificatesByActivityTrueAndCertificateTypeId(identifier);
    }

    @Override
    public Certificate save(CertificateDto certificateDto) {
        LOGGER.info("save");
        return repository.save(mapper.toEntity(certificateDto));
    }

    @Override
    public void saveAll(List<Certificate> list) {
        LOGGER.info("saveAll");
        repository.saveAll(list);
    }

    @Override
    public void delete(CertificateDto certificateDto) {
        LOGGER.info("delete");
        repository.delete(mapper.toEntity(certificateDto));
    }

}
