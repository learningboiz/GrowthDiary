package com.growthdiary.sessionlog.sessiontracker;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.growthdiary.sessionlog.details.Details;
import com.growthdiary.sessionlog.details.DetailsService;
import com.growthdiary.sessionlog.feedback.Feedback;
import com.growthdiary.sessionlog.feedback.FeedbackService;
import com.growthdiary.sessionlog.session.Session;
import com.growthdiary.sessionlog.session.SessionController;
import com.growthdiary.sessionlog.session.SessionDTO;
import com.growthdiary.sessionlog.session.SessionService;
import com.growthdiary.sessionlog.time.Time;
import com.growthdiary.sessionlog.time.TimeService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(SessionController.class)
public class ControllerUnitTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private DetailsService detailsService;

    @MockBean
    private FeedbackService feedbackService;

    @MockBean
    private TimeService timeService;

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
        LocalDateTime startPeriod = LocalDateTime.now();
        LocalDateTime endPeriod = LocalDateTime.now().plusMinutes(45);
        Time mockTime = new Time(startPeriod, endPeriod);

        // Create Feedback object
        Integer productivity = 4;
        String distraction = "LinkedIn";
        Feedback mockFeedback = new Feedback(productivity, distraction);

        // Create SessionDTO and Session objects
        SessionDTO mockDTO = new SessionDTO(skill, description, startPeriod, endPeriod, productivity, distraction);
        Session expectedSession = new Session(mockDetails, mockTime, mockFeedback);

        Mockito.when(sessionService.createSession(mockDTO))
                .thenReturn(expectedSession);

        mockMvc.perform(post("/session")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(mockDTO)))
                .andExpect(status().isCreated());
    }
}