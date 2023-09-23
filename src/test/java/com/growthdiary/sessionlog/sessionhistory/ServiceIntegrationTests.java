package com.growthdiary.sessionlog.sessionhistory;

import com.growthdiary.sessionlog.details.Details;
import com.growthdiary.sessionlog.history.HistoryService;
import com.growthdiary.sessionlog.session.Session;
import com.growthdiary.sessionlog.session.SessionRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@SpringBootTest
public class ServiceIntegrationTests {

    @Autowired
    private SessionRepository sessionRepository;

    @Autowired
    private HistoryService historyService;

    @Test
    public void testFilterBySingleSkill() {
        List<String> singleSkill = new ArrayList<>();
        String expectedSkill = "Spring Boot";
        singleSkill.add(expectedSkill);
        List<Session> singleSession = historyService.filterBySkill(singleSkill);

        Iterator<Session> singleSessionIterator = singleSession.iterator();
        String actualSkill = singleSessionIterator.next().getDetails().getSkill();
        assertEquals(expectedSkill, actualSkill);
    }

    @Test
    public void testFilterByMultipleSkills() {

        List<String> multipleSkills = new ArrayList<>();
        String expectedSkillOne = "React";
        String expectedSkillTwo = "Django";
        String expectedSkillThree = "Hashmaps";
        multipleSkills.add(expectedSkillOne);
        multipleSkills.add(expectedSkillTwo);
        multipleSkills.add(expectedSkillThree);

        List<Session> multipleSessions = historyService.filterBySkill(multipleSkills);
        Iterator<Session> multipleSessionIterator = multipleSessions.iterator();
        for (int i = 0; i < multipleSessions.size(); i++) {
            String actualSkill = multipleSessionIterator.next().getDetails().getSkill();
            assertTrue(multipleSkills.contains(actualSkill));
        }
    }

    @Test
    public void testFilterWithFaultySkills() {
        List<String> multipleSkills = new ArrayList<>();
        String expectedSkillOne = "React";
        String expectedSkillTwo = "Django";
        String faultySkill = "rando843~";
        multipleSkills.add(expectedSkillOne);
        multipleSkills.add(expectedSkillTwo);
        multipleSkills.add(faultySkill);
        List<Session> multipleSessions = historyService.filterBySkill(multipleSkills);
        Iterator<Session> multipleSessionIterator = multipleSessions.iterator();
        for (int i = 0; i < multipleSessions.size(); i++) {
            String actualSkill = multipleSessionIterator.next().getDetails().getSkill();
            assertTrue(multipleSkills.contains(actualSkill));
            assertNotEquals(actualSkill, faultySkill);
        }
    }

    @Test
    public void testFilterByDescription() {
        String validKeyword = "web application";
        List<Session> validSessions = historyService.filterByDescription(validKeyword);
        assertFalse(validSessions.isEmpty());


        System.out.println("Number of sessions: " + validSessions.size());

        Iterator<Session> javaSessionIterator = validSessions.listIterator();
        for (int i = 0; i < validSessions.size(); i++) {
            Details entryDetail = javaSessionIterator.next().getDetails();
            String returnedDescription = entryDetail.getDescription();
            assertTrue(returnedDescription.contains(validKeyword));
        }

        String invalidKeyword = "itscraazzyouthere";
        List<Session> invalidSessions = historyService.filterByDescription(invalidKeyword);
        assertTrue(invalidSessions.isEmpty());
    }

    @Test
    public void testFilterByDuration() {
        // TODO: Create test to verify specification for under/over/between duration range
    }

    @Test
    public void testFilterByDate() {
        // TODO: Create test to verify specification for before/after/between date range
    }

    @Test
    public void testFilterByProductivity() {
        // TODO: Create test to verify specification for under/over/between productivity range
    }

    @Test
    public void testFilterByDistractions() {
        // TODO: Create test to verify specified distractions
    }


}
