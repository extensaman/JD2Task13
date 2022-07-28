package by.academy.it.task13.service.impl;

import by.academy.it.task13.dto.TelegramOrderLogDto;
import by.academy.it.task13.entity.TelegramOrderLog;
import by.academy.it.task13.mapper.Mapper;
import by.academy.it.task13.repo.TelegramOrderLogRepository;
import by.academy.it.task13.service.TelegramOrderLogService;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TelegramOrderLogServiceImpl
        implements TelegramOrderLogService {
    private static final Logger logger = LogManager.getLogger(TelegramOrderLogServiceImpl.class);

    private final TelegramOrderLogRepository repository;
    private final Mapper<TelegramOrderLog, TelegramOrderLogDto> mapper;

    @Override
    public TelegramOrderLog save(TelegramOrderLogDto logDto) {
        logger.info("save");
        return repository.save(mapper.toEntity(logDto));
    }

    @Override
    public Optional<TelegramOrderLogDto> findById(Long id) {
        logger.info("findById");
        return repository.findById(id).map(mapper::toDto);
    }

    @Override
    public List<TelegramOrderLogDto> findAll() {
        logger.info("findAll");
        List<TelegramOrderLogDto> list = new ArrayList<>();
        repository.findAll().forEach(log ->
                list.add(mapper.toDto(log)));
        return list;
    }
}
