package com.growthdiary.sessionlog.sessionhistory;

import com.growthdiary.sessionlog.history.filters.FilterOperators;
import com.growthdiary.sessionlog.history.HistoryDTO;
import com.growthdiary.sessionlog.history.HistoryService;
import com.growthdiary.sessionlog.history.filters.FilterRequest;
import com.growthdiary.sessionlog.tracker.session.Session;
import com.growthdiary.sessionlog.tracker.session.SessionRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
public class ServiceIntegrationTests {

    @Autowired
    private SessionRepository sessionRepository;

    @Autowired
    private HistoryService historyService;

    private Sort createSort() {
        return Sort.by("time.startDate").descending();
    }

    private FilterRequest createDetailsFilter() {
        String entity = "details";
        String property = "skill";
        String skillA = "Spring Boot";
        String skillB = "TypeScript";

        List<String> skillList = new ArrayList<>();
        skillList.add(skillA);
        skillList.add(skillB);

        FilterRequest filter = new FilterRequest.Builder()
                .entity(entity)
                .operator(FilterOperators.IN)
                .property(property)
                .skills(skillList)
                .build();

        return filter;
    }

    private FilterRequest createTimeFilter() {
        String entity = "time";
        String property = "duration";
        Long durationA = 30L;
        Long durationB = 90L;
        List<Long> durationList = new ArrayList<>();
        durationList.add(durationA);
        durationList.add(durationB);

        FilterRequest filter = new FilterRequest.Builder()
                .entity(entity)
                .property(property)
                .operator(FilterOperators.BETWEEN)
                .durations(durationList)
                .build();

        return filter;
    }

    private FilterRequest createFeedbackFilter() {
        String entity = "feedback";
        String property = "productivity";
        int ratingA = 2;
        int ratingB = 4;
        List<Integer> productivityList = new ArrayList<>();
        productivityList.add(ratingA);
        productivityList.add(ratingB);

        FilterRequest filter = new FilterRequest.Builder()
                .entity(entity)
                .property(property)
                .operator(FilterOperators.GREATER_THAN)
                .productivity(productivityList)
                .build();

        return filter;
    }

    private List<FilterRequest> createFilterList() {
        FilterRequest detailsFilter = createDetailsFilter();
        FilterRequest timeFilter = createTimeFilter();
        FilterRequest feedbackFilter = createFeedbackFilter();

        List<FilterRequest> filters = new ArrayList<>();
        filters.add(detailsFilter);
        filters.add(timeFilter);
        filters.add(feedbackFilter);

        return filters;
    }

    @Test
    public void testHistoryServiceWithFilter() {
        List<FilterRequest> filterRequests = createFilterList();
        Sort sortRequest = createSort();
        int pageNum = 0;
        int pageSize = 10;

        HistoryDTO historyDTO = new HistoryDTO(filterRequests, pageNum, pageSize, sortRequest);

        Page<Session> sessions = historyService.getRequestedSessions(historyDTO);
        for (Session session : sessions) {
            System.out.println(session.getTime().getStartDate());
        }
    }

    @Test
    public void testHistoryServiceNoFilter() {
        Sort sortRequest = createSort();
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
