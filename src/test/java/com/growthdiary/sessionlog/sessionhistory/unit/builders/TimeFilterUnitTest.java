package com.growthdiary.sessionlog.sessionhistory.unit.builders;

import com.growthdiary.sessionlog.history.historyfilter.FilterOperations;
import com.growthdiary.sessionlog.history.historyfilter.TimeFilter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class TimeFilterUnitTest {

    private FilterOperations dateOperation;
    private LocalDate primaryDate;
    private LocalDate secondaryDate;
    private FilterOperations durationOperation;
    private Long primaryDuration;
    private Long secondaryDuration;

    @BeforeEach
    public void createDummyValues() {

        dateOperation = FilterOperations.EQUALS;
        primaryDate = LocalDate.of(2023, 10, 5);
        secondaryDate = LocalDate.of(2023, 10, 6);

        durationOperation = FilterOperations.GREATER_THAN;
        primaryDuration = 30L;
        secondaryDuration = 90L;
    }

    @Test
    public void createBothDateAndDurationFilter() {

        TimeFilter timeFilter = new TimeFilter.BuildFilter()
                .findDatesBetween(primaryDate, secondaryDate)
                .findDurationsBetween(primaryDuration, secondaryDuration)
                .build();

        assertEquals(timeFilter.getPrimaryDate(), primaryDate);
        assertEquals(timeFilter.getSecondaryDate(), secondaryDate);
        assertEquals(timeFilter.getDateOperation(), FilterOperations.BETWEEN);

        assertEquals(timeFilter.getPrimaryDuration(), primaryDuration);
        assertEquals(timeFilter.getSecondaryDuration(), secondaryDuration);
        assertEquals(timeFilter.getDurationOperation(), FilterOperations.BETWEEN);
    }

    @Test
    public void createOnlyDateFilter() {

        TimeFilter justDateFilter = new TimeFilter.BuildFilter()
                .findDates(dateOperation, primaryDate)
                .build();

        assertEquals(justDateFilter.getDateOperation(), dateOperation);
        assertEquals(justDateFilter.getPrimaryDate(), primaryDate);
        assertNull(justDateFilter.getPrimaryDuration());
    }

    @Test
    public void createOnlyDurationFilter() {

        TimeFilter justDurationFilter = new TimeFilter.BuildFilter()
                .findDuration(durationOperation, primaryDuration)
                .build();

        assertEquals(justDurationFilter.getDurationOperation(), durationOperation);
        assertEquals(justDurationFilter.getPrimaryDuration(), primaryDuration);
        assertNull(justDurationFilter.getPrimaryDate());
    }

    @Test
    public void filterTakesTheLatestArgument() {

        LocalDate latestDate = LocalDate.of(2023, 11, 21);
        Long latestDuration = 70L;

        TimeFilter timeFilter = new TimeFilter.BuildFilter()
                .findDates(dateOperation, primaryDate)
                .findDuration(durationOperation, primaryDuration)
                .findDates(dateOperation, latestDate)
                .findDuration(durationOperation, latestDuration)
                .build();

        assertEquals(latestDate, timeFilter.getPrimaryDate());
        assertEquals(latestDuration, timeFilter.getPrimaryDuration());
    }
}
