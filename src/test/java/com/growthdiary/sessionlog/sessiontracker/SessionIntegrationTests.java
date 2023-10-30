package com.growthdiary.sessionlog.sessiontracker;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.growthdiary.sessionlog.tracker.details.Details;
import com.growthdiary.sessionlog.tracker.feedback.Feedback;
import com.growthdiary.sessionlog.tracker.session.SessionController;
import com.growthdiary.sessionlog.tracker.session.SessionDTO;
import com.growthdiary.sessionlog.tracker.session.SessionRepository;
import com.growthdiary.sessionlog.tracker.session.SessionService;
import com.growthdiary.sessionlog.tracker.time.Time;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.time.LocalTime;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class SessionIntegrationTests {

    @Autowired
    private SessionRepository sessionRepository;

    @Autowired
    private SessionService sessionService;

    @Autowired
    private SessionController sessionController;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    private Details details;
    private Time time;
    private Feedback feedback;

    @BeforeEach
    public void createDummyValues() {
        details = new Details("Programming", "Learning basic loops");

        time = new Time(LocalDate.of(2023,10,4),
                        LocalDate.of(2023, 10, 4),
                        LocalTime.of(9,30),
                        LocalTime.of(10,0),
                        30L);

        feedback = new Feedback(3, "Reddit");
    }

    @Test
    public void testSessionCreation() throws Exception {
        SessionDTO sessionDTO = new SessionDTO(details, time, feedback);

        mockMvc.perform(post("/session")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(sessionDTO)))
                .andExpect(status().isCreated());
    }

    @Test
    public void testDTOWithNullValues() throws Exception {
        SessionDTO nullDTO = new SessionDTO(null, null, null);

        mockMvc.perform(post("/session")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(nullDTO)))
                .andExpect(status().isBadRequest());
    }


    @Test
    public void testNestedObjectsWithNullAttributes() throws Exception {

        Details invalidDetails = new Details(null, null);
        Time invalidTime = new Time(null, null, null, null, null);
        Feedback invalidFeedback = new Feedback(null, null);
        SessionDTO invalidDTO = new SessionDTO(invalidDetails, invalidTime, invalidFeedback);

        mockMvc.perform(post("/session")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(invalidDTO)))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void testInvalidDates() throws Exception {
        LocalDate invalidStartDate = LocalDate.of(2023, 10, 9); // start should not come after end
        LocalDate endDate = LocalDate.of(2023, 5, 9);
        LocalTime startTime = LocalTime.now();
        LocalTime endTime = LocalTime.now().plusMinutes(45);
        Long duration = 45L;

        Time invalidTime = new Time(invalidStartDate, endDate, startTime, endTime, duration);
        SessionDTO invalidDTO = new SessionDTO(details, invalidTime, feedback);

        mockMvc.perform(post("/session")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(invalidDTO)))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void testInvalidDuration() throws Exception {
        LocalDate startDate = LocalDate.of(2023, 10, 9);
        LocalDate endDate = LocalDate.of(2023, 10, 9);
        LocalTime startTime = LocalTime.now();
        LocalTime endTime = LocalTime.now().plusMinutes(45);
        Long invalidDuration = -99L;

        Time invalidTime = new Time(startDate, endDate, startTime, endTime, invalidDuration);
        SessionDTO invalidDTO = new SessionDTO(details, invalidTime, feedback);

        mockMvc.perform(post("/session")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(invalidDTO)))
                .andExpect(status().isBadRequest());
    }
}
