package by.academy.it.task13.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class AttachmentDto{
    private Long id;
    private String fileName;
    private LocalDateTime created;
}
