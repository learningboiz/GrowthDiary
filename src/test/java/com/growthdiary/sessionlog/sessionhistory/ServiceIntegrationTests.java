package com.growthdiary.sessionlog.sessionhistory;

import com.growthdiary.sessionlog.details.Details;
import com.growthdiary.sessionlog.history.FilterOperators;
import com.growthdiary.sessionlog.history.HistoryService;
import com.growthdiary.sessionlog.session.Session;
import com.growthdiary.sessionlog.session.SessionRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

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
        String validKeyword = "API";
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
    public void testFilterByDurationX() {
        FilterOperators givenOperator = FilterOperators.GREATER_THAN;
        Long benchmarkDuration = 50L;
        List<Long> durations = new ArrayList<>();
        durations.add(benchmarkDuration);

        List<Session> durationSessions = historyService.filterByDuration(givenOperator, durations);
        Iterator<Session> durationSessionsIterator = durationSessions.iterator();
        for (int i = 0; i < durationSessions.size(); i++) {
            Long actualDuration = durationSessionsIterator.next().getTime().getDuration();
            System.out.println(actualDuration);
            assertTrue(actualDuration.compareTo(benchmarkDuration) > 0);
        }
    }

    @Test
    public void testFilterByDuration() {
        FilterOperators givenComparison;
        Random random = new Random();
        List<Long> givenDurationList = new ArrayList<>();
        Long firstGivenDuration = 10L;
        Long secondGivenDuration = 35L;
        givenDurationList.add(firstGivenDuration);
        givenDurationList.add(secondGivenDuration);

        int MAX = 20;
        for (int i = 0; i < MAX; i++) {
            int OP = random.nextInt(4);

            givenComparison = switch (OP) {
                case 0 -> FilterOperators.GREATER_THAN;
                case 1 -> FilterOperators.LESS_THAN;
                case 2 -> FilterOperators.EQUALS;
                default -> FilterOperators.BETWEEN;
            };

            List<Session> sessionsBasedOnDuration = historyService.filterByDuration(givenComparison, givenDurationList);
            for (Session session : sessionsBasedOnDuration) {
                Long actualDuration = session.getTime().getDuration();

                switch (givenComparison) {
                    case GREATER_THAN -> assertTrue(actualDuration.compareTo(firstGivenDuration) > 0);
                    case LESS_THAN -> assertTrue(actualDuration.compareTo(firstGivenDuration) < 0);
                    case EQUALS -> assertTrue(actualDuration.compareTo(firstGivenDuration) == 0);
                    case BETWEEN ->
                            assertTrue(actualDuration.compareTo(firstGivenDuration) == 0 ||
                                    actualDuration.compareTo(secondGivenDuration) == 0 ||
                                    actualDuration.compareTo(firstGivenDuration) > 0 &&
                                            actualDuration.compareTo(secondGivenDuration) < 0);
                }
            }
        }
    }

    @Test
    public void testFilterByDate() {
        FilterOperators givenComparison;
        Random random = new Random();
        List<LocalDate> givenDateList = new ArrayList<>();
        LocalDate givenDateOne = LocalDate.parse("2023-09-21");
        LocalDate givenDateTwo = LocalDate.parse("2023-09-25");
        givenDateList.add(givenDateOne);
        givenDateList.add(givenDateTwo);

        int MAX = 20;
        for (int i = 0; i < MAX; i++) {
            int OP = random.nextInt(4);

            givenComparison = switch (OP) {
                case 0 -> FilterOperators.GREATER_THAN;
                case 1 -> FilterOperators.LESS_THAN;
                case 2 -> FilterOperators.EQUALS;
                default -> FilterOperators.BETWEEN;
            };

            List<Session> sessionsBasedOnDate = historyService.filterByDate(givenComparison, givenDateList);
            for (Session session : sessionsBasedOnDate) {
                LocalDate actualDate = session.getTime().getStartDate();

                switch (givenComparison) {
                    case GREATER_THAN -> assertTrue(actualDate.isAfter(givenDateOne));
                    case LESS_THAN -> assertTrue(actualDate.isBefore(givenDateOne));
                    case EQUALS -> assertEquals(actualDate, givenDateOne);
                    case BETWEEN -> assertTrue(actualDate.isEqual(givenDateOne) ||
                            actualDate.isEqual(givenDateTwo) ||
                            actualDate.isAfter(givenDateOne) && actualDate.isBefore(givenDateTwo));
                }
            }
        }
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
