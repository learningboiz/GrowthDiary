package com.growthdiary.sessionlog.sessionhistory;

import com.growthdiary.sessionlog.history.requests.SortRequest;
import org.junit.jupiter.api.Test;
import org.springframework.data.domain.Sort;

import static org.junit.jupiter.api.Assertions.*;

public class SortRequestUnitTests {
    @Test
    public void testSortAscending() {
        String property = "duration";
        Sort expectedSort = Sort.by(property).ascending();

        Sort actualSort = SortRequest.sortByAscending(property);

        assertEquals(expectedSort, actualSort);
    }

    @Test
    public void testSortDescending() {
        String property = "duration";
        Sort expectedSort = Sort.by(property).descending();

        Sort actualSort = SortRequest.sortByDescending(property);

        assertEquals(expectedSort, actualSort);
    }

    @Test
    public void testInvalidProperty() {
        String property = "description";

        assertThrows(IllegalArgumentException.class, () -> {
            Sort illegalSort1 = SortRequest.sortByAscending(property);
        });

        assertThrows(IllegalArgumentException.class, () -> {
            Sort illegalSort2 = SortRequest.sortByDescending(property);
        });
    }

    @Test
    public void testNullProperty() {
        assertThrows(IllegalArgumentException.class, () -> {
            Sort illegalSort = SortRequest.sortByAscending(null);
        });
    }

    @Test
    public void testEmptyString() {
        assertThrows(IllegalArgumentException.class, () -> {
            Sort illegalSort = SortRequest.sortByAscending("");
        });
    }
}
