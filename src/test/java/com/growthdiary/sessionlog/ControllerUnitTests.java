package com.growthdiary.sessionlog;

import com.growthdiary.sessionlog.details.Details;
import com.growthdiary.sessionlog.details.DetailsController;
import com.growthdiary.sessionlog.details.DetailsService;
import com.growthdiary.sessionlog.feedback.Feedback;
import com.growthdiary.sessionlog.feedback.FeedbackController;
import com.growthdiary.sessionlog.feedback.FeedbackService;
import com.growthdiary.sessionlog.time.TimeController;
import com.growthdiary.sessionlog.time.TimeService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest({DetailsController.class, FeedbackController.class, TimeController.class})
public class ControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private DetailsService detailsService;

    @MockBean
    private FeedbackService feedbackService;

    @MockBean
    private TimeService timeService;

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

    @Test
    public void testTimeController() throws Exception {

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

}