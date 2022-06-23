package by.academy.it.task13.controller.converter;

import by.academy.it.task13.dto.TestDto;
import by.academy.it.task13.entity.User;
import org.springframework.core.convert.converter.Converter;

public class TestDtoConverter implements Converter<String, TestDto> {

    @Override
    public TestDto convert(String source) {
        return new TestDto(source);
    }
}
