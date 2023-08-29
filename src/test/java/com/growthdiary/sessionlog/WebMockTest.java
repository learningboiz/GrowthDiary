package com.growthdiary.sessionlog;

import com.growthdiary.sessionlog.session.*;
import com.growthdiary.sessionlog.sessionhistory.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;


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
        mockMvc.perform(post("/session")
                        .param("startDate", "2023-08-26")
                        .param("endDate", "2023-08-26")
                        .param("startTime", "10:00:00")
                        .param("endTime", "12:00:00")
                        .param("topic", "Test Topic")
                        .param("category", "Test Category")
                        .param("rating", "4")
                        .param("distraction", "None")
                        .param("emotion", "Happy"))
                .andExpect(status().isCreated());
    }

    @Test
    public void testDefaultHistory() throws Exception {
        mockMvc.perform(get("/history"))
                .andExpect(status().isAccepted());
    }

    @Test
    public void testCustomHistory() throws Exception {
        mockMvc.perform(get("/history/custom")
                        .param("currentPage", "0")
                        .param("numItems", "10"))
                .andExpect(status().isAccepted());
    }

}
