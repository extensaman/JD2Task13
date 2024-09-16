package by.academy.it.task13.mapper.impl;

import by.academy.it.task13.dto.HorseDto;
import by.academy.it.task13.entity.Horse;
import by.academy.it.task13.mapper.Mapper;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
@RequiredArgsConstructor
public class HorseMapper implements Mapper<Horse, HorseDto> {
    private final ModelMapper mapper;

    @Override
    public Horse toEntity(HorseDto dto) {
        return Objects.isNull(dto) ? null : mapper.map(dto, Horse.class);
    }

    @Override
    public HorseDto toDto(Horse coach) {
        return Objects.isNull(coach) ? null : mapper.map(coach, HorseDto.class);
    }
}
