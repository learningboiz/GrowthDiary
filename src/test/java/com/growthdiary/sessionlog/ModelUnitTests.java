package com.growthdiary.sessionlog;

import com.growthdiary.sessionlog.feedback.Feedback;
import com.growthdiary.sessionlog.session.Session;
import com.growthdiary.sessionlog.skill.Skill;

import java.time.*;
import java.util.*;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
public class ModelUnitTests {

    @Test
    public void sanitySkillTests() {

        Skill test = new Skill();
        String testTopic = "Spring Boot";
        String testCategory = "Backend Development";

        test.setTopic(testTopic);
        test.setCategory(testCategory);

        String topRes = test.getTopic();
        String catRes = test.getCategory();
        assertEquals(testTopic, topRes);
        assertEquals(testCategory, catRes);
    }

    @Test
    public void sanityFeedbackTests() {

        Feedback test = new Feedback();
        Integer testRating = 5;
        String testDistraction = "YouTube";
        String testEmotion = "Stressed";

        test.setProductivity(testRating);
        test.setDistractions(testDistraction);
        test.setEmotions(testEmotion);

        assertEquals(testRating, test.getProductivity());
        assertEquals(testDistraction, test.getDistractions());
        assertEquals(testEmotion, test.getEmotions());

        test.setEmotions("Happy");
        assertNotEquals(testEmotion, test.getEmotions());
    }

    @Test
    public void sanitySessionTests() {

        Session test = new Session();

        LocalDate testDate = LocalDate.now();
        LocalTime testStartTime = LocalTime.now();
        LocalTime testEndTime = LocalTime.now();

        test.setDate(testDate);
        test.setStartTime(testStartTime);
        test.setEndTime(testEndTime);

        assertEquals(testDate, test.getDate());
        assertEquals(testStartTime, test.getStartTime());
        assertEquals(testEndTime, test.getEndTime());

        Skill testSkill = new Skill();
        Feedback testFeedback = new Feedback();

        test.setSkill(testSkill);
        test.setFeedback(testFeedback);

        assertEquals(testSkill, test.getSkill());
        assertEquals(testFeedback, test.getFeedback());
    }

    @Test
    public void testSkillSession() {
        Skill testSkill = new Skill();
        testSkill.setTopic("Java");
        testSkill.setCategory("Backend Development");

        LocalDate testDate = LocalDate.now();

        Random randNum = new Random();

        int max = 500;
        for (int i = 0; i < max; i++) {
            LocalDate newDate = testDate.plusDays(1);

            Session testSession = new Session();
            testSession.setSkill(testSkill);
            testSession.setDate(newDate);

            testSkill.addSession(testSession);
        }

        int size = testSkill.getSessions().size();
        assertEquals(max, size);
    }

}
