package by.academy.it.task13.mapper.impl;

import by.academy.it.task13.dto.user.UserNameDto;
import by.academy.it.task13.entity.User;
import by.academy.it.task13.mapper.Mapper;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
@RequiredArgsConstructor
public class UserNameMapper implements Mapper<User, UserNameDto> {
    private final ModelMapper mapper;

    @Override
    public User toEntity(UserNameDto dto) {
        return Objects.isNull(dto) ? null : mapper.map(dto, User.class);
    }

    @Override
    public UserNameDto toDto(User entity) {
        return Objects.isNull(entity) ? null : mapper.map(entity, UserNameDto.class);
    }
}
