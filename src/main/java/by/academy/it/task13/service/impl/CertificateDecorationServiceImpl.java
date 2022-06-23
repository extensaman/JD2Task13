package by.academy.it.task13.service.impl;

import by.academy.it.task13.dto.CertificateDecorationDto;
import by.academy.it.task13.entity.CertificateDecoration;
import by.academy.it.task13.mapper.Mapper;
import by.academy.it.task13.repo.CertificateDecorationRepository;
import by.academy.it.task13.service.CertificateDecorationService;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CertificateDecorationServiceImpl implements CertificateDecorationService {
    private static final Logger LOGGER = LogManager.getLogger(CertificateDecorationServiceImpl.class);

    private final CertificateDecorationRepository repository;
    private final Mapper<CertificateDecoration, CertificateDecorationDto> mapper;

    @Override
    public Optional<CertificateDecorationDto> findById(String id) {
        Optional<CertificateDecoration> decoration;
        try {
            decoration = repository.findById(Long.parseLong(id));
        } catch (NumberFormatException e) {
            decoration = Optional.empty();
        }
        return decoration.map(mapper::toDto);
    }

    @Override
    public List<CertificateDecorationDto> findAll() {
        LOGGER.info("findAll");
        List<CertificateDecorationDto> certificateDecorationDtos = new ArrayList<>();
        for (CertificateDecoration certificateDecoration : repository.findAll()) {
            certificateDecorationDtos.add(mapper.toDto(certificateDecoration));
        }
        return certificateDecorationDtos;
    }

    @Override
    public List<CertificateDecorationDto> findAllActiveCertificateDecoration() {
        LOGGER.info("findAllActiveCertificateDecoration");
        return repository.findCertificateDecorationsByActivityTrue().stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public CertificateDecoration save(CertificateDecorationDto certificateDecorationDto) {
        LOGGER.info("save");
        return repository.save(mapper.toEntity(certificateDecorationDto));
    }

    @Override
    public void saveAll(List<CertificateDecoration> list) {
        LOGGER.info("saveAll");
        repository.saveAll(list);
    }

    @Override
    public void delete(CertificateDecorationDto certificateDecorationDto) {
        LOGGER.info("delete");
        repository.delete(mapper.toEntity(certificateDecorationDto));
    }

}
