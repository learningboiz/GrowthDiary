package com.growthdiary.sessionlog.sessionhistory;

import com.growthdiary.sessionlog.history.filters.FilterOperations;
import com.growthdiary.sessionlog.history.HistoryDTO;
import com.growthdiary.sessionlog.history.HistoryService;
import com.growthdiary.sessionlog.history.requests.FilterRequest;
import com.growthdiary.sessionlog.history.requests.SortRequest;
import com.growthdiary.sessionlog.tracker.session.Session;
import com.growthdiary.sessionlog.tracker.session.SessionRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.List;

@SpringBootTest
public class HistoryServiceIntegrationTests {

    @Autowired
    private SessionRepository sessionRepository;

    @Autowired
    private HistoryService historyService;

    private Sort sortRequest;
    private FilterRequest filterRequest;

    private int totalEntries;
    private List<String> expectedSkills;
    private Long expectedMinDuration;
    private Long expectedMaxDuration;
    private int expectedMinProd;
    private int expectedMaxProd;


    @BeforeEach
    public void setRequestValues() {
        // dummy values
        totalEntries = 100;
        expectedSkills = Arrays.asList("Spring Boot", "TypeScript");
        expectedMinDuration = 30L;
        expectedMaxDuration = 90L;
        expectedMinProd = 2;
        expectedMaxProd = 3;
        String sortMapping = "time.startDate";

        // build requests
        sortRequest = SortRequest.sortByDescending(sortMapping);
        filterRequest = new FilterRequest.BuildRequest()
                .filterSkill(expectedSkills)
                .filterDuration(expectedMinDuration, expectedMaxDuration, FilterOperations.BETWEEN)
                .filterProductivity(expectedMinProd, expectedMaxProd, FilterOperations.BETWEEN)
                .build();
    }

    @Test
    public void testHistoryServiceWithFilter() {
        int pageNum = 0;
        int pageSize = 10;

        HistoryDTO historyDTO = new HistoryDTO(filterRequest, pageNum, pageSize, sortRequest);

        Page<Session> sessions = historyService.getRequestedSessions(historyDTO);
        for (Session session : sessions) {
            String actualSkill = session.getDetails().getSkill();
            Long actualDuration = session.getTime().getDuration();
            int actualProductivity = session.getFeedback().getProductivity();
            assertTrue(expectedSkills.contains(actualSkill));
            assertTrue(actualDuration >= expectedMinDuration && actualDuration <= expectedMaxDuration);
            assertTrue(actualProductivity >= expectedMinProd && actualProductivity <= expectedMaxProd);
        }
    }

    @Test
    public void testHistoryServiceNoFilter() {
        int pageNum = 0;
        int pageSize = 10;
        HistoryDTO historyDTO = new HistoryDTO(null, pageNum, pageSize, sortRequest);
        Page<Session> sessions = historyService.getRequestedSessions(historyDTO);

        assertEquals(totalEntries, sessions.getTotalElements());
    }

    @Test
    public void testNegativePageNumAndSize() {
        int pageNum = -4783;
        int pageSize = -100;
        HistoryDTO historyDTO = new HistoryDTO(null, pageNum, pageSize, sortRequest);

        assertThrows(IllegalArgumentException.class, () -> { // exception built into page request
            Page<Session> sessions = historyService.getRequestedSessions(historyDTO);
        });
    }

    @Test
    public void testPageSizeGreaterThanSessionCount() {
        int pageNum = 0;
        int pageSize = totalEntries + 10;
        HistoryDTO historyDTO = new HistoryDTO(null, pageNum, pageSize, sortRequest);
        assertThrows(IllegalArgumentException.class, () -> { // exception built into page request
            Page<Session> sessions = historyService.getRequestedSessions(historyDTO);
        });
    }
}
