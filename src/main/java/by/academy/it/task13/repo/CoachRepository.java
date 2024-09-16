package by.academy.it.task13.repo;

import by.academy.it.task13.entity.Coach;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CoachRepository extends CrudRepository<Coach, Long> {

    List<Coach> findCoachesByActivityTrue();
}
