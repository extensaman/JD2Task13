package by.academy.it.task13.dto;

import by.academy.it.task13.dto.certificate.CertificateDto;
import by.academy.it.task13.dto.certificatedecoration.CertificateDecorationDto;
import by.academy.it.task13.dto.user.UserDto;
import by.academy.it.task13.entity.OrderStatus;
import by.academy.it.task13.entity.OrderType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Optional;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class CertificateOrderDto implements Sendable{
    public static final String ID_STRING = "ID: ";
    public static final String USER_NAME_STRING = "\nUser name: ";
    public static final String CERTIFICATE_NAME_STRING = "\nCertificate name: ";
    public static final String CERTIFICATE_DECORATION_STRING = "\nCertificate decoration: ";
    public static final String EVENT_DATE_STRING = "\nEvent date: ";
    public static final String DETAILS_STRING = "\nDetails: ";
    public static final String OWNER_STRING = "\nOwner: ";
    public static final String EMPTY_STRING = "";

    private Long id;

    private OrderStatus orderStatus;

    @NotBlank(message = "{validation.details_not_blank}")
    private String details;

    @NotBlank(message = "{validation.owner_not_blank}")
    private String owner;

    @NotNull(message = "{validation.eventdate_not_null}")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate eventDate;

    private UserDto user;

    private CertificateDto certificate;

    @NotNull(message = "{validation.decoration_not_null}")
    private CertificateDecorationDto certificateDecoration;

    @DateTimeFormat(pattern="yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime created;

    @DateTimeFormat(pattern="yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime updated;

    @Override
    public String getReceiver() {
        return Optional.ofNullable(user).map(UserDto::getEmail).orElse(null);
    }

    @Override
    public String getMessage() {
        return new StringBuilder()
                .append(ID_STRING)
                .append(id)
                .append(USER_NAME_STRING)
                .append(Optional.ofNullable(user).map(UserDto::getUsername).orElse(EMPTY_STRING))
                .append(CERTIFICATE_NAME_STRING)
                .append(Optional.ofNullable(certificate).map(CertificateDto::getName).orElse(EMPTY_STRING))
                .append(CERTIFICATE_DECORATION_STRING)
                .append(Optional.ofNullable(certificateDecoration).map(CertificateDecorationDto::getName).orElse(EMPTY_STRING))
                .append(EVENT_DATE_STRING)
                .append(Optional.ofNullable(eventDate).map(LocalDate::toString).orElse(EMPTY_STRING))
                .append(OWNER_STRING)
                .append(Optional.ofNullable(owner).map(String::toString).orElse(EMPTY_STRING))
                .append(DETAILS_STRING)
                .append(Optional.ofNullable(details).map(String::toString).orElse(EMPTY_STRING))
                .toString();
    }

    @Override
    public OrderType getOrderType() {
        return OrderType.CERTIFICATE;
    }

    @Override
    public Long getOrderId() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CertificateOrderDto that = (CertificateOrderDto) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
