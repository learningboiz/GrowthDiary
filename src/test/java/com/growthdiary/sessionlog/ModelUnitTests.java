package com.growthdiary.sessionlog;

import com.growthdiary.sessionlog.feedback.Feedback;
import com.growthdiary.sessionlog.skill.Skill;
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

}
