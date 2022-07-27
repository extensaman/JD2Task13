package by.academy.it.task13.mapper.impl;

import by.academy.it.task13.dto.AttachmentDto;
import by.academy.it.task13.entity.Attachment;
import by.academy.it.task13.mapper.Mapper;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
@RequiredArgsConstructor
public class AttachmentMapper implements Mapper<Attachment, AttachmentDto> {
    private final ModelMapper mapper;

    @Override
    public Attachment toEntity(AttachmentDto dto) {
        return Objects.isNull(dto) ? null : mapper.map(dto, Attachment.class);
    }

    @Override
    public AttachmentDto toDto(Attachment entity) {
        return Objects.isNull(entity) ? null : mapper.map(entity, AttachmentDto.class);
    }
}
