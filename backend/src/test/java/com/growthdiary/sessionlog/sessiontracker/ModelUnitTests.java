package com.growthdiary.sessionlog.sessiontracker;

import com.growthdiary.sessionlog.tracker.models.Details;
import com.growthdiary.sessionlog.tracker.models.Feedback;
import com.growthdiary.sessionlog.tracker.models.Session;
import com.growthdiary.sessionlog.tracker.SessionDTO;
import com.growthdiary.sessionlog.tracker.models.Time;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.*;

public class ModelUnitTests {

    private String skill;
    private String description;
    private LocalDate startDate;
    private LocalTime startTime;
    private Long duration;
    private Integer productivity;
    private String distraction;


    @BeforeEach
    public void setDummyValues() {
        skill = "Java";
        description = "Creating a Spring Boot application";
        startDate = LocalDate.now();
        startTime = LocalTime.now();
        duration = 45L;
        productivity = 5;
        distraction = "Reddit";
    }

    @Test
    public void testDetailsConstructor() {
        Details details = new Details(skill, description);
        assertEquals(skill, details.getSkill());
        assertEquals(description, details.getDescription());
    }

    @Test
    public void testTimeConstructor() {
        Time time = new Time(startDate, startTime, duration);
        assertEquals(startDate, time.getStartDate());
        assertEquals(startTime, time.getStartTime());
        assertEquals(duration, time.getDuration());
    }

    @Test
    public void testFeedbackConstructor() {
        Feedback feedback = new Feedback(productivity, distraction);
        assertEquals(productivity, feedback.getProductivity());
        assertEquals(distraction, feedback.getDistraction());
    }
    @Test
    public void testSessionDTOCreation() {
        Details details = new Details(skill, description);
        Time time = new Time(startDate, startTime, duration);
        Feedback feedback = new Feedback(productivity, distraction);

        SessionDTO sessionDTO = new SessionDTO(details, time, feedback);
        assertEquals(details, sessionDTO.getDetails());
        assertEquals(time, sessionDTO.getTime());
        assertEquals(feedback, sessionDTO.getFeedback());
    }
    @Test
    public void testSessionConstructor() {
        Details details = new Details(skill, description);
        Time time = new Time(startDate, startTime, duration);
        Feedback feedback = new Feedback(productivity, distraction);

        Session session = new Session(details, time, feedback);
        assertEquals(details, session.getDetails());
        assertEquals(time, session.getTime());
        assertEquals(feedback, session.getFeedback());
    }
}
