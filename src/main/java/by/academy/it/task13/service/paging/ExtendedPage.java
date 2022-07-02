package by.academy.it.task13.service.paging;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;

@AllArgsConstructor
public class ExtendedPage<T> {
    private final Page<T> page;
    private final PaginationControl control;
}
