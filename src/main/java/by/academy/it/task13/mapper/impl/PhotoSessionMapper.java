package by.academy.it.task13.mapper.impl;

import by.academy.it.task13.dto.PhotoSessionDto;
import by.academy.it.task13.entity.PhotoSession;
import by.academy.it.task13.mapper.Mapper;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
@RequiredArgsConstructor
public class PhotoSessionMapper implements Mapper<PhotoSession, PhotoSessionDto> {
    private final ModelMapper mapper;

    @Override
    public PhotoSession toEntity(PhotoSessionDto dto) {
        return Objects.isNull(dto) ? null : mapper.map(dto, PhotoSession.class);
    }

    @Override
    public PhotoSessionDto toDto(PhotoSession entity) {
        return Objects.isNull(entity) ? null : mapper.map(entity, PhotoSessionDto.class);
    }
}
