package com.growthdiary.sessionlog.sessionhistory;

import com.growthdiary.sessionlog.history.HistoryRepository;
import com.growthdiary.sessionlog.history.dtos.SortRequestDTO;
import com.growthdiary.sessionlog.history.historyfilter.DetailsFilter;
import com.growthdiary.sessionlog.history.historyfilter.FeedbackFilter;
import com.growthdiary.sessionlog.history.dtos.HistoryViewDTO;
import com.growthdiary.sessionlog.history.HistoryService;
import com.growthdiary.sessionlog.history.dtos.FilterRequestDTO;
import com.growthdiary.sessionlog.history.historyfilter.TimeFilter;
import com.growthdiary.sessionlog.history.historysort.SortDirection;
import com.growthdiary.sessionlog.tracker.session.Session;
import jakarta.validation.ValidationException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

@SpringBootTest
public class HistoryServiceIntegrationTests {

    @Autowired
    private HistoryRepository historyRepository;

    @Autowired
    private HistoryService historyService;

    private DetailsFilter detailsFilter;
    private TimeFilter timeFilter;
    private FeedbackFilter feedbackFilter;
    private FilterRequestDTO filterRequestDTO;
    private HistoryViewDTO historyViewDTO;
    private SortRequestDTO sortRequestDTO;

    @BeforeEach
    public void createTestValues() {
        detailsFilter = createDetailsFilter();
        timeFilter = createTimeFilter();
        feedbackFilter = createFeedbackFilter();
        filterRequestDTO = new FilterRequestDTO(detailsFilter, timeFilter, feedbackFilter);
        historyViewDTO = new HistoryViewDTO(0, 10);
        sortRequestDTO = createSortRequestDTO();
    }

    /* --------------------------------------- Basic functionality ------------------------------------------------- */

    @Test
    public void testGetDefaultSessions() {
        Page<Session> sessions = historyService.getDefaultSessions();


        Long expectedSessions = historyRepository.count();
        Long actualSessions = sessions.getTotalElements();

        assertEquals(expectedSessions, actualSessions);
        System.out.println("Total sessions: " + expectedSessions);
    }

    @Test
    public void testGetRequestedSessions() {
        Page<Session> sessions = historyService.getRequestedSessions(filterRequestDTO, historyViewDTO, sortRequestDTO);
        for (Session session : sessions) {

            // check that all skill entries are found in the given list
            String actualSkill = session.getDetails().getSkill();
            List<String> expectedSkills = filterRequestDTO.getDetailsFilter().getSkills();
            assertTrue(expectedSkills.contains(actualSkill));
            System.out.println(session);
        }
    }

    @Test
    public void testRequestWithNoFilters() {
        Page<Session> sessions = historyService.getRequestedSessions(null, historyViewDTO, sortRequestDTO);
        Long expectedSessions = historyRepository.count();
        Long actualSessions = sessions.getTotalElements();

        assertEquals(expectedSessions, actualSessions);
    }

    @Test
    public void testRequestWithOnlyFilters() {
        assertThrows(IllegalArgumentException.class, () -> {
            Page<Session> sessions = historyService.getRequestedSessions(filterRequestDTO, null, null);
        });
    }

    @Test
    public void testRequestWithEitherViewOrSortOnly() {
        assertThrows(IllegalArgumentException.class, () -> {
            Page<Session> sessions = historyService.getRequestedSessions(null, historyViewDTO, null);
        });

        assertThrows(IllegalArgumentException.class, () -> {
            Page<Session> sessions = historyService.getRequestedSessions(null, null, sortRequestDTO);
        });
    }

    /* --------------------------------------- Validation checks --------------------------------------------------- */

    @Test
    public void testNullDetails() {
        DetailsFilter nullDetails = new DetailsFilter.BuildFilter()
                .findSkillIn(null)
                .findDescriptionLike(null)
                .build();

        FilterRequestDTO nullDetailsDTO = new FilterRequestDTO(nullDetails, timeFilter, feedbackFilter);
        assertThrows(ValidationException.class, () -> {
            Page<Session> sessions = historyService.getRequestedSessions(nullDetailsDTO, historyViewDTO, sortRequestDTO);
        });
    }

    @Test
    public void testNullTime() {
        TimeFilter nullTime = new TimeFilter.BuildFilter()
                .findDurationsBetween(null, null)
                .build();

        FilterRequestDTO nullTimeDTO = new FilterRequestDTO(detailsFilter, nullTime, feedbackFilter);
        assertThrows(ValidationException.class, () -> {
            Page<Session> sessions = historyService.getRequestedSessions(nullTimeDTO, historyViewDTO, sortRequestDTO);
        });
    }

    @Test
    public void testNegativeDuration() {
        TimeFilter negativeDuration = new TimeFilter.BuildFilter()
                .findDurationsBetween(-10L, 15L)
                .build();

        FilterRequestDTO invalidDurationDTO = new FilterRequestDTO(detailsFilter, negativeDuration, feedbackFilter);
        assertThrows(ValidationException.class, () -> {
            Page<Session> sessions = historyService.getRequestedSessions(invalidDurationDTO, historyViewDTO, sortRequestDTO);
        });
    }

    @Test
    public void testIllogicalDuration() {

    }





    /* ------------------------------------------ Private methods -------------------------------------------------- */

    private DetailsFilter createDetailsFilter() {
        List<String> skills = Arrays.asList("Spring Boot", "TypeScript");

        return new DetailsFilter.BuildFilter()
                .findSkillIn(skills)
                .build();
    }

    private TimeFilter createTimeFilter() {
        Long primaryDuration = 30L;
        Long secondaryDuration = 60L;

        return new TimeFilter.BuildFilter()
                .findDurationsBetween(primaryDuration, secondaryDuration)
                .build();
    }

    private FeedbackFilter createFeedbackFilter() {
        int primaryProductivity = 2;
        int secondaryProductivity = 4;

        return new FeedbackFilter.BuildFilter()
                .findProductivityBetween(primaryProductivity, secondaryProductivity)
                .build();
    }

    private SortRequestDTO createSortRequestDTO() {
        String sortProperty = "time.startDate";
        SortDirection sortDirection = SortDirection.DESC;
        return new SortRequestDTO(sortProperty, sortDirection);
    }
}
