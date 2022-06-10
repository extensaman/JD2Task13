package by.academy.it.task13.service;

import by.academy.it.task13.entity.PhotoSession;

import java.util.List;

public interface PhotoSessionService {
    List<PhotoSession> findAll();

    void saveAll(List<PhotoSession> list);

    PhotoSession save(PhotoSession photoSession);

    void delete(PhotoSession photoSession);
}
