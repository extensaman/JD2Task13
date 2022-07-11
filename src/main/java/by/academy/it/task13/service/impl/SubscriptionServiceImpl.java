package by.academy.it.task13.service.impl;

import by.academy.it.task13.dto.SubscriptionDto;
import by.academy.it.task13.entity.Subscription;
import by.academy.it.task13.mapper.impl.SubscriptionMapper;
import by.academy.it.task13.repo.SubscriptionRepository;
import by.academy.it.task13.service.SubscriptionService;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SubscriptionServiceImpl implements SubscriptionService {
    private static final Logger LOGGER = LogManager.getLogger(SubscriptionServiceImpl.class);

    private final SubscriptionRepository repository;
    private final SubscriptionMapper mapper;

    @Override
    public Optional<SubscriptionDto> findById(String id) {
        LOGGER.info("findById");
        Optional<SubscriptionDto> optional;
        try{
            optional = repository.findById(Long.parseLong(id)).map(mapper::toDto);
        } catch (NumberFormatException e){
            optional = Optional.empty();
        }
        return optional;
    }

    @Override
    public List<SubscriptionDto> findAll() {
        LOGGER.info("findAll");
        List<SubscriptionDto> subscriptionDtos = new ArrayList<>();
        for(Subscription subscription : repository.findAll()){
            subscriptionDtos.add(mapper.toDto(subscription));
        }
        return subscriptionDtos;
    }

    @Override
    public Subscription save(SubscriptionDto subscriptionDto) {
        LOGGER.info("save");
        return repository.save(mapper.toEntity(subscriptionDto));
    }

    @Override
    public void saveAll(List<Subscription> list) {
        LOGGER.info("saveAll");
        repository.saveAll(list);
    }

    @Override
    public void delete(SubscriptionDto subscriptionDto) {
        LOGGER.info("delete");
        repository.delete(mapper.toEntity(subscriptionDto));
    }
}
