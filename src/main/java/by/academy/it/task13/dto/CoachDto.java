package by.academy.it.task13.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class CoachDto {
    private Long id;
    private boolean activity;
    private String name;
    private String description;
    private String photoFile;
}
