package com.growthdiary.sessionlog.sessioninput;

import com.growthdiary.sessionlog.details.Details;
import com.growthdiary.sessionlog.feedback.Feedback;
import com.growthdiary.sessionlog.session.Session;
import com.growthdiary.sessionlog.session.SessionDTO;
import com.growthdiary.sessionlog.time.Time;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

import static org.junit.jupiter.api.Assertions.*;

public class ModelUnitTests {

    @Test
    public void testDetailsConstructor() {
        String skill = "Spring Boot";
        String description = "Building web applications";

        Details details = new Details(skill, description);
        assertEquals(skill, details.getSkill());
        assertEquals(description, details.getDescription());
    }

    @Test
    public void testTimeConstructor() {
        LocalDateTime startPeriod = LocalDateTime.now();
        LocalDateTime endPeriod = LocalDateTime.now().plusMinutes(45);
        Long duration = ChronoUnit.MINUTES.between(startPeriod, endPeriod);

        Time time = new Time(startPeriod, endPeriod);
        assertEquals(startPeriod.toLocalDate(), time.getStartDate());
        assertEquals(endPeriod.toLocalDate(), time.getEndDate());
        assertEquals(duration, time.getDuration());
    }

    @Test
    public void testFeedbackConstructor() {
        Integer productivity = 3;
        String distraction = "YouTube";

        Feedback feedback = new Feedback(productivity, distraction);
        assertEquals(productivity, feedback.getProductivity());
        assertEquals(distraction, feedback.getDistraction());
    }

    @Test
    public void testSessionConstructor() {

        // Create details
        String skill = "Spring Boot";
        String description = "Building web applications";
        Details details = new Details(skill, description);

        // Create time
        LocalDateTime startPeriod = LocalDateTime.now();
        LocalDateTime endPeriod = LocalDateTime.now().plusMinutes(45);
        Time time = new Time(startPeriod, endPeriod);

        // Create feedback
        Integer productivity = 3;
        String distraction = "YouTube";
        Feedback feedback = new Feedback(productivity, distraction);

        Session session = new Session(details, time, feedback);
        assertEquals(details, session.getDetails());
        assertEquals(time, session.getTime());
        assertEquals(feedback, session.getFeedback());
    }
}
