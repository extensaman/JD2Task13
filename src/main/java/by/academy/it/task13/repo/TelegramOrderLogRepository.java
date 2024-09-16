package by.academy.it.task13.repo;

import by.academy.it.task13.entity.TelegramOrderLog;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TelegramOrderLogRepository
        extends CrudRepository<TelegramOrderLog, Long> {

    @Query("select log.id from TelegramOrderLog log where log.telegramSubscriber.chatId = :id")
    List<Long> findTelegramOrderLogIdsByTelegramSubscriberChatId(@Param("id") String chadId);
}
