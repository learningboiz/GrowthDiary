package com.growthdiary.sessionlog;

import com.growthdiary.sessionlog.feedback.Feedback;
import com.growthdiary.sessionlog.session.Session;
import com.growthdiary.sessionlog.sessiontime.SessionTime;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;

import static org.junit.jupiter.api.Assertions.*;

public class SessionLogUnitTests {

    public void sessionSanityTest() {

    }

    @Test
    public void timeSanityTest() {
        LocalDate firstDate = LocalDate.now();
        LocalDate lastDate = LocalDate.now().plusDays(1);

        LocalTime startTime = LocalTime.now();
        LocalTime endTime = LocalTime.now().plusMinutes(40);

        SessionTime test = new SessionTime();
        test.setStartDate(firstDate);
        test.setEndDate(lastDate);
        test.setStartTime(startTime);
        test.setEndTime(endTime);


        Long duration = test.calculateDuration();
        test.setDuration(duration);

        assertEquals(firstDate, test.getFirstDate());
        assertEquals(lastDate, test.getLastDate());
        assertEquals(startTime, test.getStartTime());
        assertEquals(endTime, test.getEndTime());
        assertEquals(duration, test.getDuration());
    }

    @Test
    public void feedbackSanityTest() {
        int rating = 3;
        String distraction = "facebook";

        Feedback test = new Feedback();
        test.setProductivity(rating);
        test.setDistraction(distraction);

        assertEquals(rating, test.getProductivity());
        assertEquals(distraction, test.getDistraction());
    }
}
