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

    private List<String> obstacles;

    private FilterOperations productivityOperation;
    private Integer primaryProductivity;

    private Integer secondaryProductivity;

    @BeforeEach
    public void createDummyValues() {
        obstacles = Arrays.asList("Social media", "Overthinking");
        productivityOperation = FilterOperations.GREATER_THAN;
        primaryProductivity = 2;
        secondaryProductivity = 5;
    }

    @Test
    public void createBothDistractionsAndProductivity() {

        FeedbackFilter feedbackFilter = new FeedbackFilter.BuildFilter()
                .findObstacleIn(obstacles)
                .findProductivityBetween(primaryProductivity, secondaryProductivity)
                .build();

        assertEquals(obstacles, feedbackFilter.getObstacles());
        assertEquals(primaryProductivity, feedbackFilter.getPrimaryProductivity());
        assertEquals(secondaryProductivity, feedbackFilter.getSecondaryProductivity());
    }

    @Test
    public void createOnlyDistractions() {

        FeedbackFilter justObstacleFilter = new FeedbackFilter.BuildFilter()
                .findObstacleIn(obstacles)
                .build();

        assertEquals(obstacles, justObstacleFilter.getObstacles());
        assertNull(justObstacleFilter.getPrimaryProductivity());
    }

    @Test
    public void createOnlyProductivity() {

        FeedbackFilter justProductivityFilter = new FeedbackFilter.BuildFilter()
                .findProductivity(productivityOperation, primaryProductivity)
                .build();

        assertEquals(primaryProductivity, justProductivityFilter.getPrimaryProductivity());
        assertEquals(productivityOperation, justProductivityFilter.getProductivityOperation());
        assertNull(justProductivityFilter.getObstacles());
    }

    @Test
    public void filterTakesTheLatestArgument() {

        List<String> latestObstacles = Arrays.asList("Streaming services", "Lack of direction");
        Integer latestProductivity = 4;

        FeedbackFilter feedbackFilter = new FeedbackFilter.BuildFilter()
                .findObstacleIn(obstacles)
                .findProductivity(productivityOperation, primaryProductivity)
                .findObstacleIn(latestObstacles)
                .findProductivity(productivityOperation, latestProductivity)
                .build();

        assertEquals(latestObstacles, feedbackFilter.getObstacles());
        assertEquals(latestProductivity, feedbackFilter.getPrimaryProductivity());
    }
}
