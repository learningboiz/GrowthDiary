package com.growthdiary.sessionlog.sessionanalytics;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.growthdiary.sessionlog.analytics.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;

import static com.growthdiary.sessionlog.analytics.ProductivityAttributes.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class AnalyticsIntegrationTests {

    @Autowired
    private AnalyticsRepository analyticsRepository;

    @Autowired
    private AnalyticsService analyticsService;

    @Autowired
    private AnalyticsController analyticsController;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void testNullInput() {

        assertThrows(IllegalArgumentException.class, () -> {
            WeeklySummary weeklySummary = analyticsService.createWeeklySummary(null);
        });

    }

    @Test
    public void testCreateWeeklySummary() throws Exception {

        LocalDate currentDate = LocalDate.of(2023,10,15);

        mockMvc.perform(get("/session/analytics")
                        .param("currentDate", currentDate.toString()))
                .andExpect(status().isAccepted());
    }

    @Test
    public void testDurationCorrelation() throws Exception {

        mockMvc.perform(get("/session/analytics/productivity")
                .param("attribute", duration.toString()))
                .andExpect(status().isAccepted());
    }

    @Test
    public void testTimeCorrelation() throws Exception {

        mockMvc.perform(get("/session/analytics/productivity")
                        .param("attribute", time.toString()))
                .andExpect(status().isAccepted());
    }


    @Test
    public void testDistractionCorrelation() throws Exception {

        mockMvc.perform(get("/session/analytics/productivity")
                        .param("attribute", distraction.toString()))
                .andExpect(status().isAccepted());
    }

    @Test
    public void testUnsupportedAttribute() throws Exception {
        mockMvc.perform(get("/session/analytics/productivity")
                .param("attribute", "unsupported"))
                .andExpect(status().isBadRequest());
    }




}
