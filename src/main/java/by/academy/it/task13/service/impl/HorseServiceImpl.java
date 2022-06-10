package by.academy.it.task13.service.impl;

import by.academy.it.task13.entity.Horse;
import by.academy.it.task13.entity.PhotoSession;
import by.academy.it.task13.repo.HorseRepository;
import by.academy.it.task13.service.HorseService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class HorseServiceImpl implements HorseService {
    @Autowired
    private final HorseRepository repository;

    @Override
    public List<Horse> findAll(){
        return repository.findAll();
    }

    @Override
    public Horse save(Horse horse){
        return repository.save(horse);
    }

    @Override
    public void delete(Horse horse){
        repository.delete(horse);
    }

    @Override
    public void saveAll(List<Horse> list) {
        repository.saveAll(list);
    }


}
