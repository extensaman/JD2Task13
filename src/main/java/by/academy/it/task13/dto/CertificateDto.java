package by.academy.it.task13.dto;

import by.academy.it.task13.entity.Certificate;
import by.academy.it.task13.entity.CertificateType;

import java.math.BigDecimal;
import java.util.Objects;


public class CertificateDto {
    private final Long id;
    private final boolean activity;
    private final CertificateType certificateType;
    private final String name;
    private final String description;
    private final Integer horseCount;
    private final Double duration;
    private final BigDecimal price;
    private final boolean photographerIncluded;
    private final String photoFile;

    public CertificateDto(Certificate certificate) {
        this.id = certificate.getId();
        this.activity = certificate.isActivity();
        this.certificateType = certificate.getCertificateType();
        this.name = certificate.getName();
        this.description = certificate.getDescription();
        this.horseCount = certificate.getHorseCount();
        this.duration = certificate.getDuration();
        this.price = certificate.getPrice();
        this.photographerIncluded = certificate.isPhotographerIncluded();
        this.photoFile = certificate.getPhotoFile();
    }

    public Long getId() {
        return id;
    }

    public boolean isActivity() {
        return activity;
    }

    public CertificateType getCertificateType() {
        return certificateType;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Integer getHorseCount() {
        return horseCount;
    }

    public Double getDuration() {
        return duration;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public boolean isPhotographerIncluded() {
        return photographerIncluded;
    }

    public String getPhotoFile() {
        return photoFile;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CertificateDto that = (CertificateDto) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
