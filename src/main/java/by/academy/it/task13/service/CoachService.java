package by.academy.it.task13.service;

import by.academy.it.task13.entity.Coach;
import by.academy.it.task13.entity.Horse;
import by.academy.it.task13.repo.CoachRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CoachService {
    @Autowired
    private final CoachRepository repository;

    public List<Coach> findAll() {
        return repository.findAll();
    }

    public Coach save(Coach coach) {
        return repository.save(coach);
    }

    public void saveAll(List<Coach> list) {
        repository.saveAll(list);
    }


    public void delete(Coach coach) {
        repository.delete(coach);
    }
}
