package com.growthdiary.sessionlog;

import com.growthdiary.sessionlog.feedback.Feedback;
import com.growthdiary.sessionlog.session.Session;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class SessionLogUnitTests {

    public void sessionSanityTest() {

    }

    public void timeSanityTest() {

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
