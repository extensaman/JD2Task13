package by.academy.it.task13.repo;

import by.academy.it.task13.entity.TelegramSubscriber;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TelegramSubscriberRepository extends CrudRepository<TelegramSubscriber, String> {
    Optional<TelegramSubscriber> findByChatId(String chatId);
}
