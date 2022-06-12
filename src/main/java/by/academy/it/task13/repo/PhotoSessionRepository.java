package by.academy.it.task13.repo;

import by.academy.it.task13.entity.PhotoSession;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PhotoSessionRepository extends CrudRepository<PhotoSession, Long> {
    List<PhotoSession> findPhotoSessionsByActivityTrue();
}
