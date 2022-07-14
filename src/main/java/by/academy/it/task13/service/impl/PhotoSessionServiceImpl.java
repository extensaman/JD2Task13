package by.academy.it.task13.service.impl;

import by.academy.it.task13.dto.PhotoSessionDto;
import by.academy.it.task13.entity.PhotoSession;
import by.academy.it.task13.mapper.Mapper;
import by.academy.it.task13.repo.PhotoSessionRepository;
import by.academy.it.task13.service.PhotoSessionService;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PhotoSessionServiceImpl implements PhotoSessionService {
    private static final Logger LOGGER = LogManager.getLogger(PhotoSessionServiceImpl.class);

    private final PhotoSessionRepository repository;
    private final Mapper<PhotoSession, PhotoSessionDto> mapper;

    @Override
    public List<PhotoSessionDto> findAll() {
        LOGGER.info("findAll");
        List<PhotoSessionDto> photoSessionDtos = new ArrayList<>();
        for (PhotoSession photoSession : repository.findAll()) {
            photoSessionDtos.add(mapper.toDto(photoSession));
        }
        return photoSessionDtos;
    }

    @Override
    public List<PhotoSessionDto> findAllActivePhotoSession() {
        LOGGER.info("findAllActiveHorse");
        return repository.findPhotoSessionsByActivityTrue().stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public PhotoSession save(PhotoSessionDto photoSessionDto) {
        LOGGER.info("save");
        return repository.save(mapper.toEntity(photoSessionDto));
    }

    @Override
    @Transactional
    public void saveAll(List<PhotoSession> list) {
        LOGGER.info("saveAll");
        repository.saveAll(list);
    }

    @Override
    @Transactional
    public void delete(PhotoSessionDto photoSessionDto) {
        LOGGER.info("delete");
        repository.delete(mapper.toEntity(photoSessionDto));
    }
}
