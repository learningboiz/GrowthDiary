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
import com.growthdiary.sessionlog.tracker.session.Session;
import jakarta.validation.ValidationException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.internal.stubbing.defaultanswers.GloballyConfiguredAnswer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cglib.core.Local;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;

import java.time.LocalDate;
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
        List<String> expectedSkills = Arrays.asList("Django", "Python", "Java", "Spring Boot");

        DetailsFilter skillsFilter = new DetailsFilter.BuildFilter()
                .findSkillIn(expectedSkills)
                .build();

        FilterRequest skillsRequest = new FilterRequest(skillsFilter, null, null);

        SessionHistoryDTO sessionHistoryDTO = new SessionHistoryDTO(skillsRequest, pageViewRequest, sortRequest);
        Page<Session> sessions = historyService.getRequestedSessions(sessionHistoryDTO);

        for (Session session : sessions) {

            String actualSkill = session.getDetails().getSkill();
            assertTrue(expectedSkills.contains(actualSkill));
        }
    }

    @Test
    public void testValidDescriptionRequest() {
        DetailsFilter descriptionFilter = new DetailsFilter.BuildFilter()
                .findDescriptionLike("application")
                .build();

        FilterRequest descriptionRequest = new FilterRequest(descriptionFilter, null, null);
        SessionHistoryDTO sessionHistoryDTO = new SessionHistoryDTO(descriptionRequest, pageViewRequest, sortRequest);
        Page<Session> sessions = historyService.getRequestedSessions(sessionHistoryDTO);

        String expectedDescription = descriptionFilter.getDescription();
        for (Session session : sessions) {

            String actualDescription = session.getDetails().getDescription();
            assertTrue(actualDescription.contains(expectedDescription));
        }
    }

    @Test
    public void testValidDateRequest() {
        LocalDate expectedDate = LocalDate.of(2023,10,8);

        TimeFilter dateFilter = new TimeFilter.BuildFilter()
                .findDates(FilterOperations.EQUALS, expectedDate)
                .build();

        FilterRequest dateRequest = new FilterRequest(null, dateFilter, null);
        SessionHistoryDTO sessionHistoryDTO = new SessionHistoryDTO(dateRequest, pageViewRequest, sortRequest);
        Page<Session> sessions = historyService.getRequestedSessions(sessionHistoryDTO);

        for (Session session : sessions) {

            LocalDate actualDate = session.getTime().getStartDate();
            assertEquals(expectedDate, actualDate);
        }
    }

    @Test
    public void testValidDurationRequest() {
        Long minDuration = 40L;
        Long maxDuration = 100L;

        TimeFilter durationFilter = new TimeFilter.BuildFilter()
                .findDurationsBetween(minDuration, maxDuration)
                .build();

        FilterRequest durationRequest = new FilterRequest(null, durationFilter, null);
        SessionHistoryDTO sessionHistoryDTO = new SessionHistoryDTO(durationRequest, pageViewRequest, sortRequest);
        Page<Session> sessions = historyService.getRequestedSessions(sessionHistoryDTO);

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
        Page<Session> sessions = historyService.getRequestedSessions(sessionHistoryDTO);

        for (Session session : sessions) {

            int actualProductivity = session.getFeedback().getProductivity();
            assertTrue(actualProductivity >= minProductivity && actualProductivity <= maxProductivity);
        }

    }

    @Test
    public void testValidDistractionsRequest() {
        List<String> expectedDistractions = Arrays.asList("Reddit", "YouTube");

        FeedbackFilter distractionsFilter = new FeedbackFilter.BuildFilter()
                .findDistractionIn(expectedDistractions)
                .build();

        FilterRequest distractionsRequest = new FilterRequest(null, null, distractionsFilter);
        SessionHistoryDTO sessionHistoryDTO = new SessionHistoryDTO(distractionsRequest, pageViewRequest, sortRequest);
        Page<Session> sessions = historyService.getRequestedSessions(sessionHistoryDTO);

        for (Session session : sessions) {

            String actualDistraction = session.getFeedback().getDistraction();
            assertTrue(expectedDistractions.contains(actualDistraction));
        }
    }

    /*
     * Test verifies that the method runs even without filter
     * Runs as long as there is a pageview and sortrequest
     */
    @Test
    public void testRequestWithoutFilter() {
        SessionHistoryDTO noFilterDTO = new SessionHistoryDTO(null, pageViewRequest, sortRequest);
        Page<Session> sessions = historyService.getRequestedSessions(noFilterDTO);

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
    public void testRequestWithMissingPageViewOrSort() {
        DetailsFilter detailsFilter = new DetailsFilter.BuildFilter()
                .findDescriptionLike("API")
                .build();

        FilterRequest filterRequest = new FilterRequest(detailsFilter, null, null);
        SessionHistoryDTO dtoOnlyHasFilters = new SessionHistoryDTO(filterRequest, null, null);

        assertThrows(IllegalArgumentException.class, () -> {
            Page<Session> sessions = historyService.getRequestedSessions(dtoOnlyHasFilters);
        });

        SessionHistoryDTO dtoOnlyHasPageView = new SessionHistoryDTO(null, pageViewRequest, null);
        assertThrows(IllegalArgumentException.class, () -> {
            Page<Session> sessions = historyService.getRequestedSessions(dtoOnlyHasPageView);
        });

        SessionHistoryDTO dtoOnlyHasSort = new SessionHistoryDTO(null, null, sortRequest);
        assertThrows(IllegalArgumentException.class, () -> {
            Page<Session> sessions = historyService.getRequestedSessions(dtoOnlyHasSort);
        });
    }

    @Test
    public void testRequestWithInvalidFilters() {
        DetailsFilter invalidDetails = new DetailsFilter.BuildFilter()
                .findSkillIn(null)
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
            Page<Session> sessions = historyService.getRequestedSessions(invalidFilterDTO);
        });
    }
}
