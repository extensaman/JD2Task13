package by.academy.it.task13.service;

import by.academy.it.task13.entity.Certificate;
import by.academy.it.task13.entity.PhotoSession;
import by.academy.it.task13.repo.PhotoSessionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PhotoSessionService {
    @Autowired
    private final PhotoSessionRepository repository;

    public List<PhotoSession> findAll() {
        return repository.findAll();
    }

    public void saveAll(List<PhotoSession> list) {
        repository.saveAll(list);
    }

    public PhotoSession save(PhotoSession photoSession) {
        return repository.save(photoSession);
    }

    public void delete(PhotoSession photoSession) {
        repository.delete(photoSession);
    }
}
