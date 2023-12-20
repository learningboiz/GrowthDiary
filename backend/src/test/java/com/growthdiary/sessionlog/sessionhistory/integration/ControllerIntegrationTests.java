package com.growthdiary.sessionlog.sessionhistory.integration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.growthdiary.sessionlog.history.HistoryController;
import com.growthdiary.sessionlog.history.HistoryRepository;
import com.growthdiary.sessionlog.history.HistoryService;
import com.growthdiary.sessionlog.history.requests.FilterRequest;
import com.growthdiary.sessionlog.history.requests.PageViewRequest;
import com.growthdiary.sessionlog.history.requests.SessionHistoryDTO;
import com.growthdiary.sessionlog.history.requests.SortRequest;
import com.growthdiary.sessionlog.history.historyfilter.DetailsFilter;
import com.growthdiary.sessionlog.history.historyfilter.FeedbackFilter;
import com.growthdiary.sessionlog.history.historyfilter.TimeFilter;
import com.growthdiary.sessionlog.history.historysort.SortDirection;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class ControllerIntegrationTests {

    @Autowired
    private HistoryRepository historyRepository;

    @Autowired
    private HistoryService historyService;

    @Autowired
    private HistoryController historyController;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    private static FilterRequest filterRequest;
    private static PageViewRequest pageViewRequest;
    private static SortRequest sortRequest;
    private static SessionHistoryDTO sessionHistoryDTO;

    @BeforeAll
    public static void createDummyValues() {

        DetailsFilter detailsFilter = new DetailsFilter.BuildFilter()
                .findTopicIn(Arrays.asList("NodeJS", "Spring Boot"))
                .build();

        TimeFilter timeFilter = new TimeFilter.BuildFilter()
                .findDurationsBetween(40L, 100L)
                .build();

        FeedbackFilter feedbackFilter = new FeedbackFilter.BuildFilter()
                .findObstacleIn(Arrays.asList("Reddit", "YouTube"))
                .build();

        filterRequest = new FilterRequest(detailsFilter, timeFilter, feedbackFilter);
        pageViewRequest = new PageViewRequest(0, 10);
        sortRequest = new SortRequest("time.duration", SortDirection.DESC);

        sessionHistoryDTO = new SessionHistoryDTO(filterRequest, pageViewRequest, sortRequest);
    }

    @Test
    public void testDefaultSessionHistory() throws Exception {

        mockMvc.perform(get("/session/history"))
                .andExpect(status().isAccepted());
    }

    @Test
    public void testRequestedSessionHistory() throws Exception {

        mockMvc.perform(post("/session/history/search")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(sessionHistoryDTO)))
                .andExpect(status().isAccepted());
    }

    @Test
    public void testRequestWithNoFilters() throws Exception {
        SessionHistoryDTO noFilterDTO = new SessionHistoryDTO(null, pageViewRequest, sortRequest);

        mockMvc.perform(post("/session/history/search")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(noFilterDTO)))
                .andExpect(status().isAccepted());
    }

    @Test
    public void testRequestWithMissingPageViewOrSort() throws Exception {
        SessionHistoryDTO noViewSortDTO = new SessionHistoryDTO(filterRequest, null, null);

        mockMvc.perform(post("/session/history/search")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(noViewSortDTO)))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void testRequestWithInvalidFilters() throws Exception {

        DetailsFilter invalidDetails = new DetailsFilter.BuildFilter()
                .findTopicIn(null)
                .build();

        TimeFilter invalidTime = new TimeFilter.BuildFilter()
                .findDurationsBetween(-40L, -100L)
                .build();

        FeedbackFilter invalidProductivity = new FeedbackFilter.BuildFilter()
                .findProductivityBetween(6,2)
                .build();

        FilterRequest invalidFilters = new FilterRequest(invalidDetails, invalidTime, invalidProductivity);
        SessionHistoryDTO invalidFiltersDTO = new SessionHistoryDTO(invalidFilters, null, null);

        mockMvc.perform(post("/session/history/search")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(invalidFiltersDTO)))
                .andExpect(status().isBadRequest());
    }
}
