package by.academy.it.task13.dto;

import by.academy.it.task13.dto.user.UserDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class CertificateOrderDto {
    private Long id;
    private boolean activity;
    private String description;
    @NotBlank(message = "{validation.owner_not_blank}")
    private String owner;
    private LocalDateTime created;
    private LocalDateTime updated;
    private UserDto user;
    private CertificateDto certificate;
    @NotNull(message = "Choose decor")
    private CertificateDecorationDto certificateDecoration;
}
