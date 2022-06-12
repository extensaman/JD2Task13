package by.academy.it.task13.service;

import by.academy.it.task13.dto.PhotoSessionDto;
import by.academy.it.task13.entity.PhotoSession;

import java.util.List;

public interface PhotoSessionService {
    List<PhotoSessionDto> findAll();

    PhotoSession save(PhotoSessionDto photoSessionDto);

    void saveAll(List<PhotoSession> list);

    void delete(PhotoSessionDto photoSessionDto);

    List<PhotoSessionDto> findAllActivePhotoSession();
}
