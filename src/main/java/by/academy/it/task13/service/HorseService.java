package by.academy.it.task13.service;

import by.academy.it.task13.entity.Horse;
import by.academy.it.task13.entity.PhotoSession;
import by.academy.it.task13.repo.HorseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class HorseService {
    @Autowired
    private final HorseRepository repository;

    public List<Horse> findAll(){
        return repository.findAll();
    }

    public Horse save(Horse horse){
        return repository.save(horse);
    }

    public void delete(Horse horse){
        repository.delete(horse);
    }

    public void saveAll(List<Horse> list) {
        repository.saveAll(list);
    }


}
