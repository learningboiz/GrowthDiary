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
        List<Session> sessionsWithSkill = historyService.filterBySkill(singleSkill);

        for (Session session : sessionsWithSkill) {
            String actualSkill = session.getDetails().getSkill();
            assertEquals(expectedSkill, actualSkill);
        }
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

        List<Session> sessionsWithSkill = historyService.filterBySkill(multipleSkills);
        for (Session session : sessionsWithSkill) {
            String actualSkill = session.getDetails().getSkill();
            assertTrue(multipleSkills.contains(actualSkill));
        }
    }

    @Test
    public void testFilterWithFaultySkills() {
        List<String> multipleSkills = new ArrayList<>();
        String legitimateSkill = "Django";
        String faultySkill = "rando843~";
        multipleSkills.add(legitimateSkill);
        multipleSkills.add(faultySkill);
        List<Session> sessionsWithFaultySkills = historyService.filterBySkill(multipleSkills);
        assertFalse(sessionsWithFaultySkills.contains(faultySkill));
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
        FilterOperators givenComparison;
        Random random = new Random();
        List<Integer> givenProductivityList = new ArrayList<>();
        int firstGivenProductivity = 3;
        int secondGivenProductivity = 5;
        givenProductivityList.add(firstGivenProductivity);
        givenProductivityList.add(secondGivenProductivity);

        int MAX = 20;
        for (int i = 0; i < MAX; i++) {
            int OP = random.nextInt(4);

            givenComparison = switch (OP) {
                case 0 -> FilterOperators.GREATER_THAN;
                case 1 -> FilterOperators.LESS_THAN;
                case 2 -> FilterOperators.EQUALS;
                default -> FilterOperators.BETWEEN;
            };

            List<Session> sessionsBasedOnProductivity = historyService.filterByProductivity(givenComparison, givenProductivityList);
            for (Session session : sessionsBasedOnProductivity) {
                int actualProductivity = session.getFeedback().getProductivity();

                switch (givenComparison) {
                    case GREATER_THAN -> assertTrue(actualProductivity > firstGivenProductivity);
                    case LESS_THAN -> assertTrue(actualProductivity < firstGivenProductivity);
                    case EQUALS -> assertEquals(actualProductivity, firstGivenProductivity);
                    case BETWEEN ->
                            assertTrue(actualProductivity >= firstGivenProductivity &&
                                    actualProductivity <= secondGivenProductivity);
                }
            }
        }
    }

    @Test
    public void testFilterBySingleDistraction() {
        List<String> singleDistraction = new ArrayList<>();
        String expectedDistraction = "Video Games";
        singleDistraction.add(expectedDistraction);
        List<Session> sessionsWithDistraction = historyService.filterByDistraction(singleDistraction);

        for (Session session : sessionsWithDistraction) {
            String actualDistraction = session.getFeedback().getDistraction();
            assertEquals(expectedDistraction, actualDistraction);
        }
    }

    @Test
    public void testFilterByMultipleDistractions() {
        List<String> multipleDistraction = new ArrayList<>();
        String firstExpectedDistraction = "Video Games";
        String secondExpectedDistraction = "Reddit";
        multipleDistraction.add(firstExpectedDistraction);
        multipleDistraction.add(secondExpectedDistraction);
        List<Session> sessionsWithDistractions = historyService.filterByDistraction(multipleDistraction);

        for (Session session : sessionsWithDistractions) {
            String actualDistraction = session.getFeedback().getDistraction();
            assertTrue(multipleDistraction.contains(actualDistraction));
        }
    }

    @Test
    public void testFilterByFaultyDistractions() {
        List<String> faultyDistractions = new ArrayList<>();
        String firstFaultyDistraction = "lazarbugs";
        String secondFaultyDistraction = "19$53++";
        faultyDistractions.add(firstFaultyDistraction);
        faultyDistractions.add(secondFaultyDistraction);

        List<Session> sessionsWithDistractions = historyService.filterByDistraction(faultyDistractions);
        assertTrue(sessionsWithDistractions.isEmpty());
    }

}
