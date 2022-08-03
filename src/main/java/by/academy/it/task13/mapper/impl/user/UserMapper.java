package by.academy.it.task13.mapper.impl.user;

import by.academy.it.task13.dto.user.UserDto;
import by.academy.it.task13.entity.User;
import by.academy.it.task13.mapper.Mapper;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
@RequiredArgsConstructor
public class UserMapper implements Mapper<User, UserDto> {
    private final PasswordEncoder encoder;
    private final ModelMapper mapper;

    @Override
    public User toEntity(UserDto dto) {
        return Objects.isNull(dto)
                ? null
                : User.builder()
                    .username(dto.getUsername())
                    .password(encoder.encode(dto.getPassword()))
                    .email(dto.getEmail())
                    .activity(dto.isActivity())
                    .build();
    }

    @Override
    public UserDto toDto(User entity) {
        return Objects.isNull(entity) ? null : mapper.map(entity, UserDto.class);
    }
}