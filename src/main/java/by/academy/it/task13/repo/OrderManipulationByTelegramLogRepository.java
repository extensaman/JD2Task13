package by.academy.it.task13.repo;

import by.academy.it.task13.entity.OrderManipulationByTelegramLog;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderManipulationByTelegramLogRepository
                    extends CrudRepository<OrderManipulationByTelegramLog, Long> {
}
