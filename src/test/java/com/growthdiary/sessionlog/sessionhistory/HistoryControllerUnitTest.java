package com.growthdiary.sessionlog.sessionhistory;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.growthdiary.sessionlog.history.HistoryController;
import com.growthdiary.sessionlog.history.HistoryDTO;
import com.growthdiary.sessionlog.history.HistoryService;
import com.growthdiary.sessionlog.tracker.session.Session;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(HistoryController.class)
public class HistoryControllerUnitTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private HistoryService historyService;

    @InjectMocks
    private HistoryController historyController;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void testControllerLoads() throws Exception {
        assertThat(historyController).isNotNull();
    }

    @Test
    public void testDefaultEndpoint() throws Exception {
        this.mockMvc.perform(get("/session/history"))
                        .andExpect(status().isAccepted());
    }

    @Test
    public void testFilterEndpoint() throws Exception {

        // create mock DTO and page objects
        HistoryDTO mockDTO = Mockito.mock(HistoryDTO.class);
        PageRequest pageRequest = PageRequest.of(0, 10);
        List<Session> sessionList = new ArrayList<>();
        Page<Session> mockSessions = new PageImpl<>(sessionList, pageRequest, 0);

        // mock history service method
        Mockito.when(historyService.getRequestedSessions(mockDTO)).thenReturn(mockSessions);

        mockMvc.perform(get("/session/history/filter")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(mockDTO)))
                .andExpect(status().isAccepted());
    }

    @Test
    public void testEmptyRequestBody() throws Exception {

        // create mock DTO and page objects
        HistoryDTO mockDTO = Mockito.mock(HistoryDTO.class);
        PageRequest pageRequest = PageRequest.of(0, 10);
        List<Session> sessionList = new ArrayList<>();
        Page<Session> mockSessions = new PageImpl<>(sessionList, pageRequest, 0);

        // mock history service method
        Mockito.when(historyService.getRequestedSessions(mockDTO)).thenReturn(mockSessions);

        mockMvc.perform(get("/session/history/filter")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(""))
                .andExpect(status().isBadRequest());
    }
}
