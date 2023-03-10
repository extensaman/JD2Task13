package by.academy.it.task13.service.paging;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
@Builder
public class PageDisplayItem {
    public static final PageDisplayItem DOTS_DISPLAY_ITEM =
            PageDisplayItem.builder()
                    .active(false)
                    .type(PageDisplayItemType.DOTS)
                    .build();

    private PageDisplayItemType type;
    private int index;
    private boolean active;
}
