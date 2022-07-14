package by.academy.it.task13.service.impl;

import by.academy.it.task13.dto.HorseDto;
import by.academy.it.task13.entity.Horse;
import by.academy.it.task13.mapper.Mapper;
import by.academy.it.task13.repo.HorseRepository;
import by.academy.it.task13.service.HorseService;
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
public class HorseServiceImpl implements HorseService {
    private static final Logger LOGGER = LogManager.getLogger(HorseServiceImpl.class);

    private final HorseRepository repository;
    private final Mapper<Horse, HorseDto> mapper;

    @Override
    public List<HorseDto> findAll() {
        LOGGER.info("findAll");
        List<HorseDto> horseDtos = new ArrayList<>();
        for (Horse horse : repository.findAll()) {
            horseDtos.add(mapper.toDto(horse));
        }
        return horseDtos;
    }

    @Override
    public List<HorseDto> findAllActiveHorse() {
        LOGGER.info("findAllActiveHorse");
        return repository.findHorsesByActivityTrue().stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public Horse save(HorseDto horseDto) {
        LOGGER.info("save");
        return repository.save(mapper.toEntity(horseDto));
    }

    @Override
    @Transactional
    public void saveAll(List<Horse> list) {
        LOGGER.info("saveAll");
        repository.saveAll(list);
    }

    @Override
    @Transactional
    public void delete(HorseDto horseDto) {
        LOGGER.info("delete");
        repository.delete(mapper.toEntity(horseDto));
    }

}
