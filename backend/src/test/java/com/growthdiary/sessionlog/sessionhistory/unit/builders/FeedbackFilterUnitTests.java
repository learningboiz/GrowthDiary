package com.growthdiary.sessionlog.sessionhistory.unit.builders;

import com.growthdiary.sessionlog.history.historyfilter.FeedbackFilter;
import com.growthdiary.sessionlog.history.historyfilter.FilterOperations;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class FeedbackFilterUnitTests {

    private List<String> distractions;

    private FilterOperations productivityOperation;
    private Integer primaryProductivity;

    private Integer secondaryProductivity;

    @BeforeEach
    public void createDummyValues() {
        distractions = Arrays.asList("Reddit", "YouTube");
        productivityOperation = FilterOperations.GREATER_THAN;
        primaryProductivity = 2;
        secondaryProductivity = 5;
    }

    @Test
    public void createBothDistractionsAndProductivity() {

        FeedbackFilter feedbackFilter = new FeedbackFilter.BuildFilter()
                .findDistractionIn(distractions)
                .findProductivityBetween(primaryProductivity, secondaryProductivity)
                .build();

        assertEquals(distractions, feedbackFilter.getDistractions());
        assertEquals(primaryProductivity, feedbackFilter.getPrimaryProductivity());
        assertEquals(secondaryProductivity, feedbackFilter.getSecondaryProductivity());
    }

    @Test
    public void createOnlyDistractions() {

        FeedbackFilter justDistractionsFilter = new FeedbackFilter.BuildFilter()
                .findDistractionIn(distractions)
                .build();

        assertEquals(distractions, justDistractionsFilter.getDistractions());
        assertNull(justDistractionsFilter.getPrimaryProductivity());
    }

    @Test
    public void createOnlyProductivity() {

        FeedbackFilter justProductivityFilter = new FeedbackFilter.BuildFilter()
                .findProductivity(productivityOperation, primaryProductivity)
                .build();

        assertEquals(primaryProductivity, justProductivityFilter.getPrimaryProductivity());
        assertEquals(productivityOperation, justProductivityFilter.getProductivityOperation());
        assertNull(justProductivityFilter.getDistractions());
    }

    @Test
    public void filterTakesTheLatestArgument() {

        List<String> latestDistractions = Arrays.asList("Netflix", "Online games");
        Integer latestProductivity = 4;

        FeedbackFilter feedbackFilter = new FeedbackFilter.BuildFilter()
                .findDistractionIn(distractions)
                .findProductivity(productivityOperation, primaryProductivity)
                .findDistractionIn(latestDistractions)
                .findProductivity(productivityOperation, latestProductivity)
                .build();

        assertEquals(latestDistractions, feedbackFilter.getDistractions());
        assertEquals(latestProductivity, feedbackFilter.getPrimaryProductivity());
    }
}
