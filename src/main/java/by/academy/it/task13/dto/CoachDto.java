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
public class CoachDto {
    private Long id;
    private boolean activity;
    private String name;
    private String description;
    private String photoFile;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CoachDto coachDto = (CoachDto) o;
        return Objects.equals(id, coachDto.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
