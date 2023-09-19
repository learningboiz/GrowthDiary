package com.growthdiary.sessionlog;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.growthdiary.sessionlog.details.Details;
import com.growthdiary.sessionlog.feedback.Feedback;
import com.growthdiary.sessionlog.session.SessionController;
import com.growthdiary.sessionlog.session.SessionService;
import com.growthdiary.sessionlog.session.SessionDTO;
import com.growthdiary.sessionlog.time.Time;
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

@WebMvcTest(SessionController.class)
public class SessionIntegrationTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private SessionService sessionService;

    @Autowired
    private ObjectMapper objectMapper;

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
