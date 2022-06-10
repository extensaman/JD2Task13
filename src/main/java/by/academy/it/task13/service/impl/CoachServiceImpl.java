package by.academy.it.task13.service.impl;

import by.academy.it.task13.dto.CoachDto;
import by.academy.it.task13.entity.Coach;
import by.academy.it.task13.mapper.Mapper;
import by.academy.it.task13.repo.CoachRepository;
import by.academy.it.task13.service.CoachService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CoachServiceImpl implements CoachService {

    private final CoachRepository repository;
    private final Mapper<Coach, CoachDto> mapper;

    @Override
    public List<CoachDto> findAll() {
        List<CoachDto> coachDtos = new ArrayList<>();
        for (Coach coach : repository.findAll()) {
            coachDtos.add(mapper.toDto(coach));
        }
        return coachDtos;
    }

    @Override
    public List<CoachDto> findAllActiveCoach() {
        return repository.findCoachesByActivity(true).stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public Coach save(CoachDto coachDto) {
        return repository.save(mapper.toEntity(coachDto));
    }

    @Override
    public void saveAll(List<Coach> list) {
        repository.saveAll(list);
    }

    @Override
    public void delete(CoachDto coachDto) {
        repository.delete(mapper.toEntity(coachDto));
    }
}
