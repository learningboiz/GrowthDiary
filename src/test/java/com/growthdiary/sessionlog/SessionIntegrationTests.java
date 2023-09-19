package com.growthdiary.sessionlog;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.growthdiary.sessionlog.details.Details;
import com.growthdiary.sessionlog.details.DetailsController;
import com.growthdiary.sessionlog.details.DetailsService;
import com.growthdiary.sessionlog.feedback.Feedback;
import com.growthdiary.sessionlog.feedback.FeedbackController;
import com.growthdiary.sessionlog.feedback.FeedbackService;
import com.growthdiary.sessionlog.session.SessionController;
import com.growthdiary.sessionlog.session.SessionService;
import com.growthdiary.sessionlog.session.SessionDTO;
import com.growthdiary.sessionlog.time.Time;
import com.growthdiary.sessionlog.time.TimeController;
import com.growthdiary.sessionlog.time.TimeService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.time.LocalTime;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest({SessionController.class, DetailsController.class, TimeController.class, FeedbackController.class} )
public class SessionIntegrationTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private SessionService sessionService;

    @MockBean
    private FeedbackService feedbackService;

    @MockBean
    private DetailsService detailsService;

    @MockBean
    private TimeService timeService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void testDetailsCreation() throws Exception {
        String skill = "Spring Boot";
        String description = "Creating a web application";

        mockMvc.perform(post("/session/details")
                        .param("skill", skill)
                        .param("description", description))
                .andExpect(status().isCreated());
    }

    @Test
    public void testTimeCreation() throws Exception {
        LocalDate startDate = LocalDate.now();
        LocalDate endDate = LocalDate.now();
        LocalTime startTime = LocalTime.now();
        LocalTime endTime = LocalTime.now().plusMinutes(45);

        mockMvc.perform(post("/session/time")
                .param("startDate", startDate.toString())
                .param("endDate", endDate.toString())
                .param("startTime", startTime.toString())
                .param("endTime", endTime.toString()))
                .andExpect(status().isCreated());
    }

    @Test
    public void testFeedbackCreation() throws Exception {
        Integer rating = 5;
        String distraction = "YouTube";

        mockMvc.perform(post("/session/feedback")
                        .param("productivity", String.valueOf(rating))
                        .param("distraction", distraction))
                .andExpect(status().isCreated());
    }

    @Test
    public void testCreateSession() throws Exception {

        // Time object
        LocalDate startDate = LocalDate.now();
        LocalDate endDate = LocalDate.now();
        LocalTime startTime = LocalTime.now();
        LocalTime endTime = LocalTime.now().plusMinutes(45);

        Time time = new Time();
        time.setStartDate(startDate);
        time.setEndDate(endDate);
        time.setStartTime(startTime);
        time.setEndTime(endTime);
        time.setDuration(time.calculateDuration());

        // Feedback object
        Feedback feedback = new Feedback();
        feedback.setProductivity(5);
        feedback.setDistraction("Instagram");

        // Details object
        Details details = new Details();
        details.setSkill("Spring Boot");
        details.setDescription("Creating web application");

        // Create sessionDTO
        SessionDTO sessionDTO = new SessionDTO();
        sessionDTO.setDetails(details);
        sessionDTO.setFeedback(feedback);
        sessionDTO.setTime(time);

        mockMvc.perform(post("/session/complete")
                .content(objectMapper.writeValueAsString(sessionDTO))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());
    }
}
