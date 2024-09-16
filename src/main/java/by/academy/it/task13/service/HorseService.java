package by.academy.it.task13.service;

import by.academy.it.task13.dto.HorseDto;
import by.academy.it.task13.entity.Horse;

import java.util.List;

public interface HorseService {
    List<HorseDto> findAll();

    Horse save(HorseDto horseDto);

    void saveAll(List<Horse> list);

    void delete(HorseDto horseDto);

    List<HorseDto> findAllActiveHorse();
}
