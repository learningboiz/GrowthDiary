package com.growthdiary.sessionlog.sessiontracker;

import com.growthdiary.sessionlog.tracker.details.Details;
import com.growthdiary.sessionlog.tracker.feedback.Feedback;
import com.growthdiary.sessionlog.tracker.session.Session;
import com.growthdiary.sessionlog.tracker.session.SessionDTO;
import com.growthdiary.sessionlog.tracker.time.Time;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;

import static org.junit.jupiter.api.Assertions.*;

public class ModelUnitTests {

    private String skill;
    private String description;
    private LocalDate startDate;
    private LocalDate endDate;
    private LocalTime startTime;
    private LocalTime endTime;
    private Long duration;
    private Integer productivity;
    private String distraction;


    @BeforeEach
    public void setDummyValues() {
        skill = "Java";
        description = "Creating a Spring Boot application";
        startDate = LocalDate.now();
        endDate = LocalDate.now();
        startTime = LocalTime.now();
        endTime = LocalTime.now().plusMinutes(45);
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
        Time time = new Time(startDate, endDate, startTime, endTime, duration);
        assertEquals(startDate, time.getStartDate());
        assertEquals(endDate, time.getEndDate());
        assertEquals(startTime, time.getStartTime());
        assertEquals(endTime, time.getEndTime());
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
        Time time = new Time(startDate, endDate, startTime, endTime, duration);
        Feedback feedback = new Feedback(productivity, distraction);

        SessionDTO sessionDTO = new SessionDTO(details, time, feedback);
        assertEquals(details, sessionDTO.getDetails());
        assertEquals(time, sessionDTO.getTime());
        assertEquals(feedback, sessionDTO.getFeedback());
    }
    @Test
    public void testSessionConstructor() {
        Details details = new Details(skill, description);
        Time time = new Time(startDate, endDate, startTime, endTime, duration);
        Feedback feedback = new Feedback(productivity, distraction);

        Session session = new Session(details, time, feedback);
        assertEquals(details, session.getDetails());
        assertEquals(time, session.getTime());
        assertEquals(feedback, session.getFeedback());
    }
}
