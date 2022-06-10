package by.academy.it.task13.service.impl;

import by.academy.it.task13.entity.CertificateDecoration;
import by.academy.it.task13.repo.CertificateDecorationRepository;
import by.academy.it.task13.service.CertificateDecorationService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CertificateDecorationServiceImpl implements CertificateDecorationService {
    @Autowired
    private final CertificateDecorationRepository repository;

    @Override
    public List<CertificateDecoration> findAll() {
        return repository.findAll();
    }

    @Override
    public void saveAll(List<CertificateDecoration> list) {
        repository.saveAll(list);
    }

    @Override
    public void save(CertificateDecoration certificateDecoration) {
        repository.save(certificateDecoration);
    }

    @Override
    public void delete(CertificateDecoration certificateDecoration) {
        repository.delete(certificateDecoration);
    }

}
