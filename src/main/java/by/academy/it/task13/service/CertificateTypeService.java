package by.academy.it.task13.service;

import by.academy.it.task13.entity.CertificateType;
import by.academy.it.task13.repo.CertificateTypeRepository;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CertificateTypeService {
    private static final Logger LOGGER = LogManager.getLogger(CertificateTypeService.class);

    @Autowired
    private final CertificateTypeRepository repository;

    public List<CertificateType> findAll() {
        return repository.findAll();
    }

    public Optional<CertificateType> findById(String id) {
        Optional<CertificateType> result;
        try {
            result = repository.findById(Long.parseLong(id));
        } catch (NumberFormatException e){
            result = Optional.empty();
        }
        return result;
    }

    public void save(CertificateType type) {
        repository.save(type);
    }

    public void saveAll(List<CertificateType> list) {
        repository.saveAll(list);
    }

}
