package by.academy.it.task13.dto.user;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class UserDto {
    private Long id;
    @NotBlank(message = "{validation.username_not_blank}")
    private String username;
    @NotBlank(message = "{validation.password_not_blank}")
    private String password;
}
