package by.academy.it.task13.service;

import by.academy.it.task13.entity.Certificate;
import by.academy.it.task13.entity.CertificateDecoration;
import by.academy.it.task13.repo.CertificateDecorationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CertificateDecorationService {
    @Autowired
    private final CertificateDecorationRepository repository;

    public List<CertificateDecoration> findAll(){
        return repository.findAll();
    }
    public void saveAll(List<CertificateDecoration> list) {
        repository.saveAll(list);
    }
    public void save(CertificateDecoration certificateDecoration) {
        repository.save(certificateDecoration);
    }

}
