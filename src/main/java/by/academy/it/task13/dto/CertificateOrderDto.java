package by.academy.it.task13.dto;

import by.academy.it.task13.dto.user.UserDto;
import by.academy.it.task13.entity.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class CertificateOrderDto {
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
}
