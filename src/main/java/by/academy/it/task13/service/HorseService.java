package by.academy.it.task13.service;

import by.academy.it.task13.entity.Horse;

import java.util.List;

public interface HorseService {
    List<Horse> findAll();

    Horse save(Horse horse);

    void delete(Horse horse);

    void saveAll(List<Horse> list);
}
