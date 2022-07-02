package by.academy.it.task13.service.paging;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

@Setter
@Getter
public class PaginationControl {
    private static final int PAGINATION_STEP = 3;
    private static final int ONE = 1;
    private static final int TWO = 2;
    private static final int FOUR = 4;
    private static final int SIX = 6;

    private boolean nextEnable;
    private boolean prevEnable;
    private int pageSize;
    private int pageNumber;

    private final List<PageDisplayItem> items = new ArrayList<>();

    public void addPageDisplayItem(int from, int to, int pageNumber){
        IntStream.range(from,to)
                .forEach(value ->
                        items.add(PageDisplayItem.builder()
                                    .active(pageNumber != value)
                                    .index(value)
                                    .type(PageDisplayItemType.PAGE)
                                    .build()));
    }

    public void last(int pageCount){
        items.add(PageDisplayItem.DOTS_DISPLAY_ITEM);
        items.add(PageDisplayItem.builder()
                .active(true)
                .index(pageCount)
                .type(PageDisplayItemType.PAGE)
                .build());
    }

    public void first(int pageNumber){
        items.add(PageDisplayItem.builder()
                .active(pageNumber != ONE)
                .index(ONE)
                .type(PageDisplayItemType.PAGE)
                .build());
        items.add(PageDisplayItem.DOTS_DISPLAY_ITEM);
    }

    public static PaginationControl of(int totalPages, int pageNumber, int pageSize){
        PaginationControl control = new PaginationControl();
        control.setPageNumber(pageNumber);
        control.setPageSize(pageSize);
        control.setNextEnable(pageNumber != totalPages);
        control.setPrevEnable(pageNumber != ONE);

        if(totalPages < PAGINATION_STEP * TWO + SIX){
            control.addPageDisplayItem(ONE, totalPages + ONE, pageNumber);
        } else if(pageNumber < PAGINATION_STEP * TWO + ONE){
            control.addPageDisplayItem(ONE, PAGINATION_STEP * TWO + FOUR, pageNumber);
            control.last(totalPages);
        } else if(pageNumber > totalPages - PAGINATION_STEP * TWO){
            control.first(pageNumber);
            control.addPageDisplayItem(totalPages - PAGINATION_STEP * TWO - TWO, totalPages + ONE, pageNumber);
        } else {
            control.first(pageNumber);
            control.addPageDisplayItem(pageNumber - PAGINATION_STEP, pageNumber + PAGINATION_STEP, pageNumber);
            control.last(totalPages);
        }
        return control;
    }
}
