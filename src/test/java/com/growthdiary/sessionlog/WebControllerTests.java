package com.growthdiary.sessionlog;

import com.growthdiary.sessionlog.session.*;
import com.growthdiary.sessionlog.sessionhistory.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;


import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest({SessionController.class, SessionHistoryController.class})
public class WebMockTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private SessionService sessionService;

    @MockBean
    private SessionHistoryService sessionHistoryService;

    @Test
    public void testCreateSession() throws Exception {

        LocalDateTime testStart = LocalDateTime.of(LocalDate.now(), LocalTime.now());
        LocalDateTime testEnd = testStart.plus(2, ChronoUnit.HOURS);
        
        mockMvc.perform(post("/session")
                        .param("startPeriod", String.valueOf(testStart))
                        .param("endPeriod", String.valueOf(testEnd))
                        .param("topic", "Test Topic")
                        .param("category", "Test Category")
                        .param("rating", "4")
                        .param("distraction", "None")
                        .param("emotion", "Happy"))
                .andExpect(status().isCreated());
    }

    /** Tests SessionHistory default view */
    @Test
    public void testDefaultHistory() throws Exception {
        mockMvc.perform(get("/history"))
                .andExpect(status().isAccepted());
    }

    /** Tests SessionHistory view with custom values */
    @Test
    public void testCustomHistory() throws Exception {
        mockMvc.perform(get("/history")
                        .param("currentPage", "0")
                        .param("numItems", "10"))
                .andExpect(status().isAccepted());
    }

}
