package by.academy.it.task13.service;

import by.academy.it.task13.dto.CertificateOrderDto;
import by.academy.it.task13.entity.CertificateOrder;
import by.academy.it.task13.service.paging.ExtendedPage;

import java.util.List;
import java.util.Optional;

public interface CertificateOrderService {
    Optional<CertificateOrderDto> findById(String id);

    List<CertificateOrderDto> findAll();

    ExtendedPage<CertificateOrderDto> getExtendedPage(int pageNumber, int size, String sortField, String sortDir);

    void saveAll(List<CertificateOrder> list);

    CertificateOrder save(CertificateOrderDto certificateOrderDto);

    void delete(CertificateOrderDto certificateOrderDto);
}
