package by.academy.it.task13.service;

import by.academy.it.task13.dto.CoachDto;
import by.academy.it.task13.entity.Coach;

import java.util.List;

public interface CoachService {
    List<CoachDto> findAll();

    Coach save(CoachDto coachDto);

    void saveAll(List<Coach> list);

    void delete(CoachDto coachDto);

    List<CoachDto> findAllActiveCoach();
}
