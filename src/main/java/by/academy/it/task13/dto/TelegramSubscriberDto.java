package by.academy.it.task13.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class TelegramSubscriberDto {
    private String chatId;
    private boolean activity;
    private String name;
}
