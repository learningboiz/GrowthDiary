package com.growthdiary.sessionlog.sessionhistory.integration;

import com.growthdiary.sessionlog.history.HistoryRepository;
import com.growthdiary.sessionlog.history.HistoryService;
import com.growthdiary.sessionlog.history.historyfilter.FilterOperations;
import com.growthdiary.sessionlog.history.requests.FilterRequest;
import com.growthdiary.sessionlog.history.requests.PageViewRequest;
import com.growthdiary.sessionlog.history.requests.SessionHistoryDTO;
import com.growthdiary.sessionlog.history.requests.SortRequest;
import com.growthdiary.sessionlog.history.historyfilter.DetailsFilter;
import com.growthdiary.sessionlog.history.historyfilter.FeedbackFilter;
import com.growthdiary.sessionlog.history.historyfilter.TimeFilter;
import com.growthdiary.sessionlog.history.historysort.SortBuilder;
import com.growthdiary.sessionlog.history.historysort.SortDirection;
import com.growthdiary.sessionlog.tracker.models.Details;
import com.growthdiary.sessionlog.tracker.models.Feedback;
import com.growthdiary.sessionlog.tracker.models.Session;
import com.growthdiary.sessionlog.tracker.models.Time;
import jakarta.validation.ValidationException;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
public class ServiceIntegrationTests {
    @Autowired
    private HistoryRepository historyRepository;

    @Autowired
    private HistoryService historyService;

    private final PageViewRequest pageViewRequest = new PageViewRequest(0, 20);
    private final SortRequest sortRequest = new SortRequest("time.duration", SortDirection.DESC);

    @BeforeAll
    public static void insertTestValues(@Autowired HistoryRepository historyRepository) {

        Session testSession1 = new Session();
        testSession1.setDetails(new Details("Spring Boot", "Building backend API"));
        testSession1.setTime(new Time(LocalDate.of(2023,10,10), LocalTime.of(9, 0), 30L));
        testSession1.setFeedback(new Feedback(3, "Social media"));

        Session testSession2 = new Session();
        testSession2.setDetails(new Details("Quicksort", "Practising Leetcode"));
        testSession2.setTime(new Time(LocalDate.of(2023,10,10), LocalTime.of(11, 30), 45L));
        testSession2.setFeedback(new Feedback(4, "Overthinking"));

        historyRepository.save(testSession1);
        historyRepository.save(testSession2);
    }

    @AfterAll
    public static void clearTestValues(@Autowired HistoryRepository historyRepository) {
        historyRepository.deleteAll();
    }

    /*
     * This test checks that the method returns all entries in the database
     * It verifies that the sort and number of pages are correct
     */
    @Test
    public void testDefaultRequest() {

        Page<Session> sessions = historyService.getDefaultSessions();

        Long expectedNumOfSessions = historyRepository.count();
        Long actualNumOfSessions = sessions.getTotalElements();

        Sort defaultSort = SortBuilder.buildSort("time.startDate", SortDirection.DESC);
        Sort actualSort = sessions.getSort();

        int defaultItemsPerPage = 10;
        int expectedNumOfPages = (int) Math.ceil((double) expectedNumOfSessions / defaultItemsPerPage);
        int actualNumOfPages = sessions.getTotalPages();

        assertEquals(expectedNumOfSessions, actualNumOfSessions);
        assertEquals(defaultSort, actualSort);
        assertEquals(expectedNumOfPages, actualNumOfPages);
    }

    @Test
    public void testValidSkillsRequest() {
        List<String> expectedTopics = Arrays.asList("Spring Boot", "Quicksort");

        DetailsFilter topicsFilter = new DetailsFilter.BuildFilter()
                .findTopicIn(expectedTopics)
                .build();

        FilterRequest topicsRequest = new FilterRequest(topicsFilter, null, null);

        SessionHistoryDTO sessionHistoryDTO = new SessionHistoryDTO(topicsRequest, pageViewRequest, sortRequest);
        Page<Session> sessions = historyService.getCustomSessions(sessionHistoryDTO);

        for (Session session : sessions) {

            String actualTopic = session.getDetails().getTopic();
            assertTrue(expectedTopics.contains(actualTopic));
        }
    }

    @Test
    public void testValidDescriptionRequest() {
        DetailsFilter descriptionFilter = new DetailsFilter.BuildFilter()
                .findDescriptionLike("API")
                .build();

        FilterRequest descriptionRequest = new FilterRequest(descriptionFilter, null, null);
        SessionHistoryDTO sessionHistoryDTO = new SessionHistoryDTO(descriptionRequest, pageViewRequest, sortRequest);
        Page<Session> sessions = historyService.getCustomSessions(sessionHistoryDTO);

        String expectedDescription = descriptionFilter.getDescription();
        for (Session session : sessions) {

            String actualDescription = session.getDetails().getDescription();
            assertTrue(actualDescription.contains(expectedDescription));
        }
    }

    @Test
    public void testValidDateRequest() {
        LocalDate expectedDate = LocalDate.of(2023,10,10);

        TimeFilter dateFilter = new TimeFilter.BuildFilter()
                .findDates(FilterOperations.EQUALS, expectedDate)
                .build();

        FilterRequest dateRequest = new FilterRequest(null, dateFilter, null);
        SessionHistoryDTO sessionHistoryDTO = new SessionHistoryDTO(dateRequest, pageViewRequest, sortRequest);
        Page<Session> sessions = historyService.getCustomSessions(sessionHistoryDTO);

        for (Session session : sessions) {

            LocalDate actualDate = session.getTime().getStartDate();
            assertEquals(expectedDate, actualDate);
        }
    }

    @Test
    public void testValidDurationRequest() {
        Long minDuration = 30L;
        Long maxDuration = 60L;

        TimeFilter durationFilter = new TimeFilter.BuildFilter()
                .findDurationsBetween(minDuration, maxDuration)
                .build();

        FilterRequest durationRequest = new FilterRequest(null, durationFilter, null);
        SessionHistoryDTO sessionHistoryDTO = new SessionHistoryDTO(durationRequest, pageViewRequest, sortRequest);
        Page<Session> sessions = historyService.getCustomSessions(sessionHistoryDTO);

        for (Session session : sessions) {

            Long actualDuration = session.getTime().getDuration();
            assertTrue(actualDuration >= minDuration && actualDuration <= maxDuration);
        }
    }

    @Test
    public void testValidProductivityRequest() {
        int minProductivity = 2;
        int maxProductivity = 4;

        FeedbackFilter productivityFilter = new FeedbackFilter.BuildFilter()
                .findProductivityBetween(minProductivity, maxProductivity)
                .build();

        FilterRequest productivityRequest = new FilterRequest(null, null, productivityFilter);
        SessionHistoryDTO sessionHistoryDTO = new SessionHistoryDTO(productivityRequest, pageViewRequest, sortRequest);
        Page<Session> sessions = historyService.getCustomSessions(sessionHistoryDTO);

        for (Session session : sessions) {

            int actualProductivity = session.getFeedback().getProductivity();
            assertTrue(actualProductivity >= minProductivity && actualProductivity <= maxProductivity);
        }

    }

    @Test
    public void testValidDistractionsRequest() {
        List<String> expectedObstacles = Arrays.asList("Social media", "Overthinking");

        FeedbackFilter obstaclesFilter = new FeedbackFilter.BuildFilter()
                .findObstacleIn(expectedObstacles)
                .build();

        FilterRequest obstaclesRequest = new FilterRequest(null, null, obstaclesFilter);
        SessionHistoryDTO sessionHistoryDTO = new SessionHistoryDTO(obstaclesRequest, pageViewRequest, sortRequest);
        Page<Session> sessions = historyService.getCustomSessions(sessionHistoryDTO);

        for (Session session : sessions) {

            String actualObstacle = session.getFeedback().getObstacle();
            assertTrue(expectedObstacles.contains(actualObstacle));
        }
    }

    /*
     * Test verifies that the method runs even without filter
     * Runs as long as there is a page-view and sortrequest
     */
    @Test
    public void testRequestWithoutFilter() {
        SessionHistoryDTO noFilterDTO = new SessionHistoryDTO(null, pageViewRequest, sortRequest);
        Page<Session> sessions = historyService.getCustomSessions(noFilterDTO);

        Long expectedNumOfSessions = historyRepository.count();
        Long actualNumOfSessions = sessions.getTotalElements();

        Sort expectedSort = SortBuilder.buildSort(sortRequest.getProperty(), sortRequest.getSortDirection());
        Sort actualSort = sessions.getSort();

        int expectedNumOfPages = (int) Math.ceil((double) expectedNumOfSessions / pageViewRequest.getPageSize());
        int actualNumOfPages = sessions.getTotalPages();

        assertEquals(expectedNumOfSessions, actualNumOfSessions);
        assertEquals(expectedSort, actualSort);
        assertEquals(expectedNumOfPages, actualNumOfPages);
    }

    @Test
    public void testRequestWithInvalidFilters() {
        DetailsFilter invalidDetails = new DetailsFilter.BuildFilter()
                .findTopicIn(null)
                .build();

        TimeFilter invalidTime = new TimeFilter.BuildFilter()
                .findDurationsBetween(-20L, 90L)
                .findDatesBetween(LocalDate.of(2023, 10, 5), LocalDate.of(2023, 5,5))
                .build();

        FeedbackFilter invalidFeedback = new FeedbackFilter.BuildFilter()
                .findProductivityBetween(77, 2)
                .build();

        FilterRequest invalidFilterRequest = new FilterRequest(null, invalidTime, null);
        SessionHistoryDTO invalidFilterDTO = new SessionHistoryDTO(invalidFilterRequest, pageViewRequest, sortRequest);

        assertThrows(ValidationException.class, () -> {
            Page<Session> sessions = historyService.getCustomSessions(invalidFilterDTO);
        });
    }
}
