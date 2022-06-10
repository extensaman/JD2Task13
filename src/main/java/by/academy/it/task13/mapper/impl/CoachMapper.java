package by.academy.it.task13.mapper.impl;

import by.academy.it.task13.dto.CoachDto;
import by.academy.it.task13.entity.Coach;
import by.academy.it.task13.mapper.Mapper;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
@RequiredArgsConstructor
public class CoachMapper implements Mapper<Coach, CoachDto> {

    private final ModelMapper mapper;

    @Override
    public Coach toEntity(CoachDto dto) {
        return Objects.isNull(dto) ? null : mapper.map(dto, Coach.class);
    }

    @Override
    public CoachDto toDto(Coach coach) {
        return Objects.isNull(coach) ? null : mapper.map(coach, CoachDto.class);
    }
}
