package by.academy.it.task13.repo;

import by.academy.it.task13.entity.PhotoSession;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PhotoSessionRepository extends JpaRepository<PhotoSession, Long> {
}
