package com.growthdiary.sessionlog.sessionhistory.unit.builders;

import com.growthdiary.sessionlog.history.historysort.SortBuilder;
import com.growthdiary.sessionlog.history.historysort.SortDirection;
import org.junit.jupiter.api.Test;
import org.springframework.data.domain.Sort;

import static org.junit.jupiter.api.Assertions.*;

public class SortBuilderUnitTests {

    @Test
    public void testSortBuilder() {
        String property = "time.duration";
        SortDirection ascending = SortDirection.ASC;
        Sort expectedAscSort = Sort.by(property).ascending();
        Sort actualAscSort = SortBuilder.buildSort(property, ascending);

        assertEquals(expectedAscSort, actualAscSort);

        SortDirection descending = SortDirection.DESC;
        Sort expectedDescSort = Sort.by(property).descending();
        Sort actualDescSort = SortBuilder.buildSort(property, descending);

        assertEquals(expectedDescSort, actualDescSort);
    }
}
