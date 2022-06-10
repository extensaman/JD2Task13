package by.academy.it.task13.service.impl;

import by.academy.it.task13.entity.PhotoSession;
import by.academy.it.task13.repo.PhotoSessionRepository;
import by.academy.it.task13.service.PhotoSessionService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PhotoSessionServiceImpl implements PhotoSessionService {
    @Autowired
    private final PhotoSessionRepository repository;

    @Override
    public List<PhotoSession> findAll() {
        return repository.findAll();
    }

    @Override
    public void saveAll(List<PhotoSession> list) {
        repository.saveAll(list);
    }

    @Override
    public PhotoSession save(PhotoSession photoSession) {
        return repository.save(photoSession);
    }

    @Override
    public void delete(PhotoSession photoSession) {
        repository.delete(photoSession);
    }
}
