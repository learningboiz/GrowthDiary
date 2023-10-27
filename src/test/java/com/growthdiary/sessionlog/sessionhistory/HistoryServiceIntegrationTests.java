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
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.List;

@SpringBootTest
public class HistoryServiceIntegrationTests {

    @Autowired
    private HistoryRepository historyRepository;

    @Autowired
    private HistoryService historyService;

    private FilterRequestDTO createFilterRequestDTO() {

        // details filter
        List<String> skills = Arrays.asList("Spring Boot", "TypeScript");

        DetailsFilter detailsFilter = new DetailsFilter.BuildFilter()
                .filterSkill(skills)
                .build();

        // time filter
        Long primaryDuration = 30L;
        Long secondaryDuration = 60L;

        TimeFilter timeFilter = new TimeFilter.BuildFilter()
                .filterDuration(primaryDuration, secondaryDuration)
                .build();

        // feedback filter
        int primaryProductivity = 2;
        int secondaryProductivity = 4;

        FeedbackFilter feedbackFilter = new FeedbackFilter.BuildFilter()
                .filterProductivity(primaryProductivity, secondaryProductivity)
                .build();

        return new FilterRequestDTO(detailsFilter, timeFilter, feedbackFilter);
    }

    private HistoryViewDTO createHistoryViewDTO() {
        int pageNum = 0;
        int pageSize = 10;
        return new HistoryViewDTO(pageNum, pageSize);
    }

    private SortRequestDTO createSortRequestDTO() {
        String sortProperty = "time.startDate";
        SortDirection sortDirection = SortDirection.DESC;
        return new SortRequestDTO(sortProperty, sortDirection);
    }

    @Test
    public void testHistoryServiceDefaultValues() {
        Page<Session> sessions = historyService.getDefaultSessions();


        Long expectedSessions = historyRepository.count();
        Long actualSessions = sessions.getTotalElements();

        assertEquals(expectedSessions, actualSessions);
        System.out.println("Total sessions: " + expectedSessions);
    }

    @Test
    public void testHistoryServiceAllDTOs() {
        FilterRequestDTO filterRequestDTO = createFilterRequestDTO();
        HistoryViewDTO historyViewDTO = createHistoryViewDTO();
        SortRequestDTO sortRequestDTO = createSortRequestDTO();

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
    public void testHistoryServiceOnlyFilters() {
        FilterRequestDTO filterRequestDTO = createFilterRequestDTO();

        Page<Session> sessions = historyService.getRequestedSessions(filterRequestDTO, null, null);
        for (Session session : sessions) {

            // check that all skill entries are found in the given list
            String actualSkill = session.getDetails().getSkill();
            List<String> expectedSkills = filterRequestDTO.getDetailsFilter().getSkills();
            assertTrue(expectedSkills.contains(actualSkill));
        }
    }

    @Test
    public void testHistoryServiceNoFilters() {
        HistoryViewDTO historyViewDTO = createHistoryViewDTO();
        SortRequestDTO sortRequestDTO = createSortRequestDTO();

        Page<Session> sessions = historyService.getRequestedSessions(null, historyViewDTO, sortRequestDTO);
        Long expectedSessions = historyRepository.count();
        Long actualSessions = sessions.getTotalElements();

        assertEquals(expectedSessions, actualSessions);
    }

    @Test
    public void testHistoryServiceNoFiltersNoSort() {
        HistoryViewDTO historyViewDTO = createHistoryViewDTO();

        Page<Session> sessions = historyService.getRequestedSessions(null, historyViewDTO, null);
        Long expectedSessions = historyRepository.count();
        Long actualSessions = sessions.getTotalElements();

        assertEquals(expectedSessions, actualSessions);
    }

    @Test
    public void testHistoryServiceNoFiltersNoView() {
        SortRequestDTO sortRequestDTO = createSortRequestDTO();

        Page<Session> sessions = historyService.getRequestedSessions(null, null, sortRequestDTO);
        Long expectedSessions = historyRepository.count();
        Long actualSessions = sessions.getTotalElements();

        assertEquals(expectedSessions, actualSessions);
    }



}
