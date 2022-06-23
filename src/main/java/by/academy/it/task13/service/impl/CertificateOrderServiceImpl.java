package by.academy.it.task13.service.impl;

import by.academy.it.task13.dto.CertificateOrderDto;
import by.academy.it.task13.entity.CertificateOrder;
import by.academy.it.task13.mapper.Mapper;
import by.academy.it.task13.repo.CertificateOrderRepository;
import by.academy.it.task13.service.CertificateOrderService;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class CertificateOrderServiceImpl implements CertificateOrderService {
    private static final Logger LOGGER = LogManager.getLogger(CertificateOrderServiceImpl.class);

    private final CertificateOrderRepository repository;
    private final Mapper<CertificateOrder, CertificateOrderDto> mapper;

    @Override
    public Optional<CertificateOrderDto> findById(String id) {
        LOGGER.info("findById");
        Optional<CertificateOrder> certificateOrder;
        try {
            certificateOrder = repository.findById(Long.parseLong(id));
        } catch (NumberFormatException e) {
            certificateOrder = Optional.empty();
        }
        return certificateOrder.map(mapper::toDto);
    }

    @Override
    public List<CertificateOrderDto> findAll() {
        LOGGER.info("findAll");

        List<CertificateOrderDto> orderDtos = new ArrayList<>();
        for (CertificateOrder order : repository.findAll()) {
            orderDtos.add(mapper.toDto(order));
        }
        return orderDtos;
    }

    @Override
    public void saveAll(List<CertificateOrder> list) {
        LOGGER.info("saveAll");
        repository.saveAll(list);
    }

    @Override
    public CertificateOrder save(CertificateOrderDto certificateOrderDto) {
        LOGGER.info("save");
        return repository.save(mapper.toEntity(certificateOrderDto));
    }
}
