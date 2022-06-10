package by.academy.it.task13.service.impl;

import by.academy.it.task13.entity.CertificateType;
import by.academy.it.task13.repo.CertificateTypeRepository;
import by.academy.it.task13.service.CertificateTypeService;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CertificateTypeServiceImpl implements CertificateTypeService {
    private static final Logger LOGGER = LogManager.getLogger(CertificateTypeServiceImpl.class);

    @Autowired
    private final CertificateTypeRepository repository;

    @Override
    public List<CertificateType> findAll() {
        return repository.findAll();
    }

    @Override
    public Optional<CertificateType> findById(String id) {
        Optional<CertificateType> result;
        try {
            result = repository.findById(Long.parseLong(id));
        } catch (NumberFormatException e){
            result = Optional.empty();
        }
        return result;
    }

    @Override
    public void save(CertificateType type) {
        repository.save(type);
    }

    @Override
    public void saveAll(List<CertificateType> list) {
        repository.saveAll(list);
    }
    @Override
    public void delete(CertificateType certificateType) {
        repository.delete(certificateType);
    }


}
