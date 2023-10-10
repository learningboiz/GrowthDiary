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
import org.springframework.data.jpa.domain.Specification;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
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

    @BeforeEach
    public void setRequestValues() {
        sortRequest = SortRequest.sortByDescending("time.startDate");

        filterRequest = new FilterRequest.BuildRequest()
                .filterSkill(Arrays.asList("Spring Boot", "TypeScript"))
                .filterDuration(30L, 90L, FilterOperations.BETWEEN)
                .filterProductivity(2, 3, FilterOperations.BETWEEN)
                .build();
    }


    @Test
    public void testHistoryServiceWithFilter() {
        int pageNum = 0;
        int pageSize = 10;

        HistoryDTO historyDTO = new HistoryDTO(filterRequest, pageNum, pageSize, sortRequest);

        Page<Session> sessions = historyService.getRequestedSessions(historyDTO);
        for (Session session : sessions) {
            System.out.println(session.getTime().getStartDate());
        }
    }

    @Test
    public void testHistoryServiceNoFilter() {
        int pageNum = 0;
        int pageSize = 10;
        Pageable pageable = PageRequest.of(pageNum, pageSize, sortRequest);
        HistoryDTO historyDTO = new HistoryDTO(null, pageNum, pageSize, sortRequest);
        Page<Session> sessions = historyService.getRequestedSessions(historyDTO);

        assertEquals(100, sessions.getTotalElements());
        for (Session session : sessions) {
            System.out.println(session.getTime().getDuration());
        }
    }
}
