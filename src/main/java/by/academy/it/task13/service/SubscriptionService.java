package by.academy.it.task13.service;

import by.academy.it.task13.dto.SubscriptionDto;
import by.academy.it.task13.dto.certificatedecoration.CertificateDecorationDto;
import by.academy.it.task13.dto.certificatedecoration.CertificateDecorationNameDto;
import by.academy.it.task13.entity.CertificateDecoration;
import by.academy.it.task13.entity.Subscription;

import java.util.List;
import java.util.Optional;

public interface SubscriptionService {
    Optional<SubscriptionDto> findById(Long id);

    List<SubscriptionDto> findAll();

    Subscription save(SubscriptionDto subscriptionDto);

    void saveAll(List<Subscription> list);

    void delete(SubscriptionDto subscriptionDto);
}
