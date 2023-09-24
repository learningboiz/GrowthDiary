package com.growthdiary.sessionlog.sessioninput;

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

    /**
     * Test case for the DetailsController /session/details endpoint
     *
     * Creates a mock DetailsService and configures it to return a Details object
     * Verifies that the method takes in correct input parameters and returns a JSON object
     * @throws Exception
     */
    @Test
    public void testDetailsController() throws Exception {

        String skill = "Spring Boot";
        String description = "Working on a web application";

        Details expectedDetails = new Details(skill, description);

        Mockito.when(detailsService.createDetails(Mockito.anyString(), Mockito.anyString()))
                        .thenReturn(expectedDetails);

        mockMvc.perform(post("/session/details")
                .param("skill", skill)
                .param("description", description)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.skill").value(skill))
                .andExpect(jsonPath("$.description").value(description))
                .andDo(print());
    }

    /**
     * Test case for the FeedbackController /session/feedback endpoint
     *
     * Creates a mock FeedbackService and configures it to return a Feedback object
     * Verifies that the method takes in correct input parameters and returns a JSON object
     * @throws Exception
     */
    @Test
    public void testFeedbackController() throws Exception {

        Integer productivity = 3;
        String distraction = "YouTube";

        Feedback expectedFeedback = new Feedback(productivity, distraction);

        Mockito.when(feedbackService.createFeedback(Mockito.anyInt(), Mockito.anyString()))
                        .thenReturn(expectedFeedback);

        mockMvc.perform(post("/session/feedback")
                        .param("productivity", productivity.toString())
                        .param("distraction", distraction)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.productivity").value(productivity))
                .andExpect(jsonPath("$.distraction").value(distraction))
                .andDo(print());
    }

    /**
     * Test case for the TimeController /session/time endpoint
     *
     * Creates a mock TimeService and configures it to return a Time object
     * Verifies that the method takes in correct input parameters and returns a JSON object
     * @throws Exception
     */
    @Test
    public void testTimeController() throws Exception {

        LocalDateTime startPeriod = LocalDateTime.now();
        LocalDateTime endPeriod = LocalDateTime.now().plusMinutes(45);

        Time expectedTime = new Time(startPeriod, endPeriod);
        String expectedDuration = expectedTime.getDuration().toString();

        Mockito.when(timeService.createTime(Mockito.any(LocalDateTime.class), Mockito.any(LocalDateTime.class)))
                .thenReturn(expectedTime);

        mockMvc.perform(post("/session/time")
                        .param("startPeriod", startPeriod.toString())
                        .param("endPeriod", endPeriod.toString())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.startPeriod").value(startPeriod.toString()))
                .andExpect(jsonPath("$.endPeriod").value(endPeriod.toString()))
                .andExpect(jsonPath("$.duration").value(expectedDuration))
                .andDo(print());
    }

    @Test
    public void testSessionController() throws Exception {

        // Create Details object
        String skill = "Hashmap";
        String description = "Leetcode to work on algorithms";
        Details mockDetails = new Details(skill, description);

        // Create Feedback object
        Integer productivity = 4;
        String distraction = "LinkedIn";
        Feedback mockFeedback = new Feedback(productivity, distraction);

        // Create Time object
        LocalDateTime startPeriod = LocalDateTime.now();
        LocalDateTime endPeriod = LocalDateTime.now().plusMinutes(45);
        Time mockTime = new Time(startPeriod, endPeriod);

        // Create SessionDTO and Session objects
        SessionDTO mockDTO = new SessionDTO(mockDetails, mockTime, mockFeedback);
        Session expectedSession = new Session(mockDetails, mockTime, mockFeedback);

        Mockito.when(sessionService.createSession(mockDTO))
                .thenReturn(expectedSession);

        mockMvc.perform(post("/session/complete")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(mockDTO)))
                .andExpect(status().isCreated());
    }
}