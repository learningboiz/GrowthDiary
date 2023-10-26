package com.growthdiary.sessionlog.sessiontracker;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.growthdiary.sessionlog.tracker.details.Details;
import com.growthdiary.sessionlog.tracker.feedback.Feedback;
import com.growthdiary.sessionlog.tracker.session.Session;
import com.growthdiary.sessionlog.tracker.session.SessionController;
import com.growthdiary.sessionlog.tracker.session.SessionDTO;
import com.growthdiary.sessionlog.tracker.session.SessionService;
import com.growthdiary.sessionlog.tracker.time.Time;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.time.LocalTime;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(SessionController.class)
public class ControllerUnitTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private SessionService sessionService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void testSessionController() throws Exception {

        // Create Details object
        String skill = "Hashmap";
        String description = "Leetcode to work on algorithms";
        Details mockDetails = new Details(skill, description);

        // Create Time object
        LocalDate startDate = LocalDate.now();
        LocalDate endDate = LocalDate.now();
        LocalTime startTime = LocalTime.now();
        LocalTime endTime = LocalTime.now().plusMinutes(45);
        Long duration = 45L;
        Time mockTime = new Time(startDate, endDate, startTime, endTime, duration);

        // Create Feedback object
        Integer productivity = 4;
        String distraction = "LinkedIn";
        Feedback mockFeedback = new Feedback(productivity, distraction);

        // Create SessionDTO and Session objects
        SessionDTO mockDTO = new SessionDTO(mockDetails, mockTime, mockFeedback);
        Session expectedSession = new Session(mockDetails, mockTime, mockFeedback);

        Mockito.when(sessionService.createSession(mockDTO))
                .thenReturn(expectedSession);

        mockMvc.perform(post("/session")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(mockDTO)))
                .andExpect(status().isCreated());
    }
}