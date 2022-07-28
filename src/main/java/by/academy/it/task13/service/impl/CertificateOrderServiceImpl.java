package by.academy.it.task13.service.impl;

import by.academy.it.task13.dto.CertificateOrderDto;
import by.academy.it.task13.entity.CertificateOrder;
import by.academy.it.task13.entity.OrderStatus;
import by.academy.it.task13.mapper.Mapper;
import by.academy.it.task13.repo.CertificateOrderRepository;
import by.academy.it.task13.service.CertificateOrderService;
import by.academy.it.task13.service.paging.ExtendedPage;
import by.academy.it.task13.service.paging.PaginationControl;
import by.academy.it.task13.service.specification.CertificateOrderSpecification;
import by.academy.it.task13.service.specification.filter.CertificateOrderFilter;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class CertificateOrderServiceImpl implements CertificateOrderService {
    private static final Logger logger = LogManager.getLogger(CertificateOrderServiceImpl.class);

    private final CertificateOrderRepository repository;
    private final Mapper<CertificateOrder, CertificateOrderDto> mapper;
    private final CertificateOrderSpecification orderSpecification;

    @Override
    public Optional<CertificateOrderDto> findById(Long id) {
        logger.info("findById");
        return repository.findById(id).map(mapper::toDto);
    }

    @Override
    public Optional<CertificateOrderDto> findByIdFetchLazy(Long id) {
        logger.info("findByIdFetchCertificate");
        return repository.findByIdFetchLazy(id).map(mapper::toDto);
    }

    @Override
    public List<CertificateOrderDto> findAll() {
        logger.info("findAll");

        List<CertificateOrderDto> orderDtos = new ArrayList<>();
        for (CertificateOrder order : repository.findAll()) {
            orderDtos.add(mapper.toDto(order));
        }
        return orderDtos;
    }

    @Override
    @Transactional
    public void saveAll(List<CertificateOrder> list) {
        logger.info("saveAll");
        repository.saveAll(list);
    }

    @Override
    @Transactional
    public CertificateOrder save(CertificateOrderDto certificateOrderDto) {
        logger.info("save");
        return repository.save(mapper.toEntity(certificateOrderDto));
    }

    @Override
    @Transactional
    public CertificateOrder save(CertificateOrder certificateOrder) {
        logger.info("save");
        return repository.save(certificateOrder);
    }

    @Override
    @Transactional
    public void delete(CertificateOrderDto certificateOrderDto) {
        logger.info("delete");
        repository.delete(mapper.toEntity(certificateOrderDto));
    }

    @Override
    public ExtendedPage<CertificateOrderDto> getExtendedPage(CertificateOrderFilter filter, int pageNumber, int size, String sortField, String sortDirection) {
        logger.info("getExtendedPage");
        Specification<CertificateOrder> specification = orderSpecification.getCertificateOrderSpecification(filter);
        Sort sort = Sort.by(sortField);
        sort = Constant.ASC.equalsIgnoreCase(sortDirection) ? sort.ascending() : sort.descending();
        PageRequest request = PageRequest.of(pageNumber - Constant.ONE, size, sort);
        Page<CertificateOrderDto> postPage = repository.findAll(specification, request).map(mapper::toDto);
        return new ExtendedPage<>(postPage, PaginationControl.of(postPage.getTotalPages(), pageNumber, size));
    }

    @Override
    public List<Long> findCertificateOrderIdsByCertificateId(Long id) {
        logger.info("findCertificateOrderIdsByCertificateId");
        return repository.findCertificateOrderIdsByCertificateId(id);
    }

    @Override
    public List<Long> findCertificateOrderIdsByCertificateDecorationId(Long id) {
        logger.info("findCertificateOrderIdsByCertificateDecorationId");
        return repository.findCertificateOrderIdsByCertificateDecorationId(id);
    }

    @Override
    public CertificateOrderDto updateAndReturnCertificateOrderStatus(CertificateOrderDto dto, OrderStatus status) {
        dto.setOrderStatus(status);
        repository.save(mapper.toEntity(dto));
        return dto;
    }
}
