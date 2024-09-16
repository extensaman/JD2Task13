package by.academy.it.task13.repo;

import by.academy.it.task13.entity.Horse;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HorseRepository extends PagingAndSortingRepository<Horse, Long> {
    List<Horse> findHorsesByActivityTrue();

}
