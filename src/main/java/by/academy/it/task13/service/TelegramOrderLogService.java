package by.academy.it.task13.service;

import by.academy.it.task13.dto.TelegramOrderLogDto;
import by.academy.it.task13.entity.TelegramOrderLog;

import java.util.List;
import java.util.Optional;

public interface TelegramOrderLogService {
    TelegramOrderLog save(TelegramOrderLogDto log);

    Optional<TelegramOrderLogDto> findById(Long id);

    List<TelegramOrderLogDto> findAll();
}
