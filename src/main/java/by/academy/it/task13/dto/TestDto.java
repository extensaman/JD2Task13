package by.academy.it.task13.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class TestDto {
    @NotBlank(message = "not blank please")
    private String name;
}
