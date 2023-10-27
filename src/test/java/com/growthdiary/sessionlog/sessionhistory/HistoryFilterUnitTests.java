package com.growthdiary.sessionlog.sessionhistory;

import com.growthdiary.sessionlog.history.historyfilter.FilterOperations;
import com.growthdiary.sessionlog.history.historyfilter.DetailsFilter;
import com.growthdiary.sessionlog.history.historyfilter.FeedbackFilter;
import com.growthdiary.sessionlog.history.historyfilter.TimeFilter;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class HistoryFilterUnitTests {

    @Test
    public void testDetailsFilterBuild() {
        // dummy values
        List<String> skills = Arrays.asList("Java", "Django");
        String description = "API";

        DetailsFilter detailsFilter = new DetailsFilter.BuildFilter()
                .filterSkill(skills)
                .filterDescription(description)
                .build();

        assertEquals(detailsFilter.getSkills(), skills);
        assertEquals(detailsFilter.getDescription(), description);
    }

    @Test
    public void testTimeFilterBuild() {
        // dummy values
        LocalDate primaryDate = LocalDate.of(2023, 10, 10);
        LocalDate secondaryDate = LocalDate.of(2023, 10, 10);
        FilterOperations dateOperation = FilterOperations.GREATER_THAN;

        Long primaryDuration = 45L;
        Long secondaryDuration = 60L;
        FilterOperations durationOperation = FilterOperations.LESS_THAN;

        TimeFilter timeFilter = new TimeFilter.BuildFilter()
                .filterDates(primaryDate, secondaryDate)
                .filterDuration(primaryDuration, durationOperation)
                .build();

        assertEquals(timeFilter.getPrimaryDate(), primaryDate);
        assertEquals(timeFilter.getSecondaryDate(), secondaryDate);
        assertEquals(timeFilter.getDateOperation(), FilterOperations.BETWEEN);
        assertEquals(timeFilter.getPrimaryDuration(), primaryDuration);
        assertEquals(timeFilter.getDurationOperation(), durationOperation);
    }

    @Test
    public void testFeedbackFilterBuild() {
        // dummy values
        int primaryProductivity = 2;
        int secondaryProductivity = 4;
        FilterOperations productivityOperation = FilterOperations.EQUALS;

        List<String> distractions = Arrays.asList("YouTube", "Reddit");

        FeedbackFilter feedbackFilter = new FeedbackFilter.BuildFilter()
                .filterDistractions(distractions)
                .filterProductivity(primaryProductivity, secondaryProductivity)
                .build();

        assertEquals(feedbackFilter.getDistractions(), distractions);
        assertEquals(feedbackFilter.getPrimaryProductivity(), primaryProductivity);
        assertEquals(feedbackFilter.getSecondaryProductivity(), secondaryProductivity);
        assertEquals(feedbackFilter.getProductivityOperation(), FilterOperations.BETWEEN);
    }
}
