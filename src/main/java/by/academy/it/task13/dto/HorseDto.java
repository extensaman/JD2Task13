package by.academy.it.task13.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.Objects;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
public class HorseDto {
    private Long id;
    private boolean activity;
    private String name;
    private String description;
    private String photoFile;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        HorseDto horseDto = (HorseDto) o;
        return Objects.equals(id, horseDto.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
