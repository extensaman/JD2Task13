package by.academy.it.task13.repo;

import by.academy.it.task13.entity.TelegramOrderLog;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TelegramOrderLogRepository
                    extends CrudRepository<TelegramOrderLog, Long> {
}
