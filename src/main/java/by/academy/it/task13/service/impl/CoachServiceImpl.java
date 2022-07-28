package by.academy.it.task13.service.impl;

import by.academy.it.task13.dto.CoachDto;
import by.academy.it.task13.entity.Coach;
import by.academy.it.task13.mapper.Mapper;
import by.academy.it.task13.repo.CoachRepository;
import by.academy.it.task13.service.CoachService;
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
public class CoachServiceImpl implements CoachService {
    private static final Logger logger = LogManager.getLogger(CoachServiceImpl.class);

    private final CoachRepository repository;
    private final Mapper<Coach, CoachDto> mapper;

    @Override
    public List<CoachDto> findAll() {
        logger.info("findAll");
        List<CoachDto> coachDtos = new ArrayList<>();
        for (Coach coach : repository.findAll()) {
            coachDtos.add(mapper.toDto(coach));
        }
        return coachDtos;
    }

    @Override
    public List<CoachDto> findAllActiveCoach() {
        logger.info("findAllActiveCoach");
        return repository.findCoachesByActivityTrue().stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public Coach save(CoachDto coachDto) {
        logger.info("save");
        return repository.save(mapper.toEntity(coachDto));
    }

    @Override
    @Transactional
    public void saveAll(List<Coach> list) {
        logger.info("saveAll");
        repository.saveAll(list);
    }

    @Override
    @Transactional
    public void delete(CoachDto coachDto) {
        logger.info("delete");
        repository.delete(mapper.toEntity(coachDto));
    }
}
