package by.academy.it.task13.service.paging;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.data.domain.Page;

@AllArgsConstructor
@Getter
public class ExtendedPage<T> {
    private final Page<T> page;
    private final PaginationControl control;
}
