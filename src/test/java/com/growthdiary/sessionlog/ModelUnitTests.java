package com.growthdiary.sessionlog;

import com.growthdiary.sessionlog.skill.Skill;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
public class ModelUnitTests {

    @Test
    public void sanityTests() {

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

}
