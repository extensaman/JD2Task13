package by.academy.it.task13.service;

import by.academy.it.task13.dto.CertificateOrderDto;
import by.academy.it.task13.entity.CertificateOrder;

import java.util.List;
import java.util.Optional;

public interface CertificateOrderService {
    Optional<CertificateOrderDto> findById(String id);

    List<CertificateOrderDto> findAll();

    void saveAll(List<CertificateOrder> list);

    CertificateOrder save(CertificateOrderDto certificateOrderDto);
}
