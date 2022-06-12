package by.academy.it.task13.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class PhotoSessionDto {
    private Long id;
    private boolean activity;
    private String name;
    private String description;
    private Integer horseCount;
    private Integer duration;
    private BigDecimal price;
    private String photoFile;
}
