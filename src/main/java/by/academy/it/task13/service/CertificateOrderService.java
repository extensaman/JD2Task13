package by.academy.it.task13.service;

import by.academy.it.task13.dto.CertificateOrderDto;
import by.academy.it.task13.entity.CertificateOrder;
import by.academy.it.task13.entity.OrderStatus;
import by.academy.it.task13.service.paging.ExtendedPage;
import by.academy.it.task13.service.specification.filter.CertificateOrderFilter;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface CertificateOrderService {
    Optional<CertificateOrderDto> findById(Long id);

    Optional<CertificateOrderDto> findByIdFetchLazy(Long id);

    List<CertificateOrderDto> findAll();

    ExtendedPage<CertificateOrderDto> getExtendedPage(CertificateOrderFilter filter, int pageNumber, int size, String sortField, String sortDir);

    void saveAll(List<CertificateOrder> list);

    CertificateOrder save(CertificateOrderDto certificateOrderDto);

    CertificateOrder save(CertificateOrder certificateOrder);

    void delete(CertificateOrderDto certificateOrderDto);

    List<Long> findCertificateOrderIdsByCertificateId(Long id);

    List<Long> findCertificateOrderIdsByCertificateDecorationId(Long id);

    CertificateOrderDto updateAndReturnCertificateOrderStatus(CertificateOrderDto dto, OrderStatus status);
}
