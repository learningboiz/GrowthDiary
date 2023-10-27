package com.growthdiary.sessionlog.sessionhistory;

import com.growthdiary.sessionlog.history.specifications.FilterOperations;
import com.growthdiary.sessionlog.history.historyfilter.DetailsFilter;
import com.growthdiary.sessionlog.history.historyfilter.FeedbackFilter;
import com.growthdiary.sessionlog.history.historyfilter.FilterRequest;
import com.growthdiary.sessionlog.history.historyfilter.TimeFilter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class FilterRequestUnitTests {

    private List<String> skills;
    private String description;

    private LocalDate minDate;
    private LocalDate maxDate;
    private Long minDuration;
    private Long maxDuration;
    private Integer minProd;
    private Integer maxProd;
    private List<String> distractions;

    @BeforeEach
    public void createDummyValues() {
        skills = Arrays.asList("Java", "Django");
        description = "API";
        minDate = LocalDate.of(2023, 10, 10);
        maxDate = LocalDate.of(2023, 10, 10);
        minDuration = 45L;
        maxDuration = 60L;
        minProd = 2;
        maxProd = 4;
        distractions = Arrays.asList("YouTube", "Reddit");
    }


    @Test
    public void sanityTestForRequests() {
        DetailsFilter detailsRequest = new DetailsFilter();
        assertNotNull(detailsRequest);

        TimeFilter timeRequest = new TimeFilter();
        assertNotNull(timeRequest);

        FeedbackFilter feedbackRequest = new FeedbackFilter();
        assertNotNull(feedbackRequest);
    }

    @Test
    public void testDetailRequestCreation() {
        DetailsFilter detailsRequest = new DetailsFilter();

        detailsRequest.setSkills(skills);
        detailsRequest.setDescription(description);

        assertEquals(skills, detailsRequest.getSkills());
        assertEquals(description, detailsRequest.getDescription());
    }

    @Test
    public void testTimeRequestCreation() {
        TimeFilter timeRequest = new TimeFilter();

        // set-up dates
        FilterOperations dateOp = FilterOperations.BETWEEN;
        timeRequest.setDateOp(dateOp);
        timeRequest.setMinDate(minDate);
        timeRequest.setMaxDate(maxDate);

        // set-up durations
        FilterOperations durationOp = FilterOperations.BETWEEN;
        timeRequest.setDurationOp(durationOp);
        timeRequest.setMinDuration(minDuration);
        timeRequest.setMaxDuration(maxDuration);

        // validate dates
        assertEquals(dateOp, timeRequest.getDateOp());
        assertEquals(minDate, timeRequest.getMinDate());
        assertEquals(maxDate, timeRequest.getMaxDate());

        // validate durations
        assertEquals(durationOp, timeRequest.getDurationOp());
        assertEquals(minDuration, timeRequest.getMinDuration());
        assertEquals(maxDuration, timeRequest.getMaxDuration());
    }

    @Test
    public void testFeedbackRequestCreation() {
        FeedbackFilter feedbackRequest = new FeedbackFilter();

        feedbackRequest.setDistractions(distractions);

        FilterOperations prodOp = FilterOperations.BETWEEN;
        feedbackRequest.setProdOp(prodOp);
        feedbackRequest.setMinProd(minProd);
        feedbackRequest.setMaxProd(maxProd);

        assertEquals(distractions, feedbackRequest.getDistractions());
        assertEquals(prodOp, feedbackRequest.getProdOp());
        assertEquals(minProd, feedbackRequest.getMinProd());
        assertEquals(maxProd, feedbackRequest.getMaxProd());
    }

    @Test
    public void testEmptyFilterRequest() {
        assertThrows(UnsupportedOperationException.class, () -> {
            FilterRequest filterRequest = new FilterRequest.BuildRequest().build();
        });
    }

    @Test
    public void testFilterRequestCreation() {
        FilterOperations op = FilterOperations.BETWEEN;

        FilterRequest filterRequest = new FilterRequest.BuildRequest()
                .filterSkill(skills)
                .filterDescription(description)
                .filterDate(minDate, maxDate, op)
                .filterDuration(minDuration, maxDuration, op)
                .filterProductivity(minProd, maxProd, op)
                .filterDistractions(distractions)
                .build();

        assertNotNull(filterRequest.getDetailsRequest().getSkills());
        assertNotNull(filterRequest.getDetailsRequest().getDescription());
        assertNotNull(filterRequest.getTimeRequest().getMinDate());
        assertNotNull(filterRequest.getTimeRequest().getMinDuration());
        assertNotNull(filterRequest.getFeedbackRequest().getMinProd());
        assertNotNull(filterRequest.getFeedbackRequest().getDistractions());
    }

    @Test
    public void testNullValuesForBetweenOperation() {
        FilterOperations op = FilterOperations.BETWEEN;

        assertThrows(IllegalArgumentException.class, () -> {
            FilterRequest filterRequest = new FilterRequest.BuildRequest().
                    filterDate(minDate, null, op)
                    .build();
        });

        assertThrows(IllegalArgumentException.class, () -> {
            FilterRequest filterRequest = new FilterRequest.BuildRequest().
                    filterDuration(minDuration, null, op)
                    .build();
        });

        assertThrows(IllegalArgumentException.class, () -> {
            FilterRequest filterRequest = new FilterRequest.BuildRequest().
                    filterProductivity(minProd, null, op)
                    .build();
        });

    }

    @Test
    public void testNegativeValues() {
        // negative durations
        assertThrows(IllegalArgumentException.class, () -> {
            FilterRequest filterRequest = new FilterRequest.BuildRequest()
                    .filterDuration(-20L, 40L, FilterOperations.BETWEEN).
                    build();
        });

        // negative productivity
        assertThrows(IllegalArgumentException.class, () -> {
            FilterRequest filterRequest = new FilterRequest.BuildRequest()
                    .filterProductivity(-70, 999, FilterOperations.BETWEEN).
                    build();
        });
    }

    @Test
    public void testMinValueGreaterThanMaxValue() {
        // durations
        assertThrows(IllegalArgumentException.class, () -> {
            FilterRequest filterRequest = new FilterRequest.BuildRequest()
                    .filterDuration(60L, 20L, FilterOperations.BETWEEN).
                    build();
        });

        // dates
        assertThrows(IllegalArgumentException.class, () -> {
            FilterRequest filterRequest = new FilterRequest.BuildRequest()
                    .filterDate(minDate.plusDays(1), maxDate, FilterOperations.BETWEEN).
                    build();
        });

        // productivity
        assertThrows(IllegalArgumentException.class, () -> {
            FilterRequest filterRequest = new FilterRequest.BuildRequest()
                    .filterProductivity(5,3 , FilterOperations.BETWEEN).
                    build();
        });
    }

    @Test
    public void testNotAllFiltersRequested() {
        // only details
        FilterRequest filterRequestDetails = new FilterRequest.BuildRequest()
                .filterSkill(skills)
                .build();

        assertNull(filterRequestDetails.getTimeRequest());
        assertNull(filterRequestDetails.getFeedbackRequest());

        // only time
        FilterRequest filterRequestTime = new FilterRequest.BuildRequest()
                .filterDuration(minDuration, null, FilterOperations.EQUALS)
                .build();

        assertNull(filterRequestTime.getDetailsRequest());
        assertNull(filterRequestTime.getFeedbackRequest());

        // only feedback
        FilterRequest filterRequestFeedback = new FilterRequest.BuildRequest()
                .filterDistractions(distractions)
                .build();

        assertNull(filterRequestFeedback.getDetailsRequest());
        assertNull(filterRequestFeedback.getTimeRequest());
    }
}
