package by.academy.it.task13.dto.user;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.util.Objects;

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
    @Email(message = "{validation.email}")
    @NotBlank(message = "{validation.email_not_blank}")
    private String email;
    private boolean activity;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserDto userDto = (UserDto) o;
        return Objects.equals(id, userDto.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
