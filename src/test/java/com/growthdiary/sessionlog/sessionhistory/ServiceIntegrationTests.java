package com.growthdiary.sessionlog.sessionhistory;

import com.growthdiary.sessionlog.history.FilterOperators;
import com.growthdiary.sessionlog.history.HistoryService;
import com.growthdiary.sessionlog.history.FilterRequest;
import com.growthdiary.sessionlog.session.Session;
import com.growthdiary.sessionlog.session.SessionRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
public class ServiceIntegrationTests {

    @Autowired
    private SessionRepository sessionRepository;

    @Autowired
    private HistoryService historyService;

    @Test
    public void testDynamicFilter() {
        // first filter details
        String entity = "details";
        String key = "skill";
        String skillA = "Spring Boot";
        String skillB = "TypeScript";

        List<String> skillList = new ArrayList<>();
        skillList.add(skillA);
        skillList.add(skillB);

        FilterRequest filterA = new FilterRequest.Builder()
                .entity(entity)
                .operator(FilterOperators.IN)
                .key(key)
                .skills(skillList)
                .build();

        // second filter time

        String entityB = "time";
        String keyB = "duration";
        Long durationA = 43L;
        Long durationB = 87L;
        List<Long> durationList = new ArrayList<>();
        durationList.add(durationA);
        durationList.add(durationB);

        FilterRequest filterB = new FilterRequest.Builder()
                .entity(entityB)
                .key(keyB)
                .operator(FilterOperators.BETWEEN)
                .durations(durationList)
                .build();

        // third filter feedback

        String entityC = "feedback";
        String keyC = "productivity";
        int ratingA = 2;
        int ratingB = 4;
        List<Integer> productivityList = new ArrayList<>();
        productivityList.add(ratingA);
        productivityList.add(ratingB);

        FilterRequest filterC = new FilterRequest.Builder()
                .entity(entityC)
                .key(keyC)
                .operator(FilterOperators.GREATER_THAN)
                .productivity(productivityList)
                .build();

        List<FilterRequest> filters = new ArrayList<>();
        filters.add(filterA);
        filters.add(filterB);
        filters.add(filterC);

        List<Session> sessions = historyService.dynamicFilter(filters);
        System.out.println("Number of results is: " + sessions.size());

        for (Session session : sessions) {
            String actualSkill = session.getDetails().getSkill();
            Long actualDuration = session.getTime().getDuration();
            int actualProductivity = session.getFeedback().getProductivity();

            assertTrue(skillList.contains(actualSkill));
            assertTrue(actualDuration >= durationA && actualDuration <= durationB);
            assertTrue(actualProductivity > ratingA);
        }
    }

    @Test
    public void testFindAll() {
        int cap = 100;

        List<Session> allSessions = historyService.getAllSessions();
        assertEquals(allSessions.size(), cap);
    }
}
