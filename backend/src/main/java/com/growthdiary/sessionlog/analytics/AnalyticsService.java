package com.growthdiary.sessionlog.analytics;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class AnalyticsService {

    private final AnalyticsRepository analyticsRepository;

    @Autowired
    public AnalyticsService(AnalyticsRepository analyticsRepository) {
        this.analyticsRepository = analyticsRepository;
    }

    public SessionStats createWeeklySummary() {

        return analyticsRepository.getSummary();
    }

    public List<ProductivityChart> createProductivityChart(ProductivityCategory category) {
        return switch (category) {
            case duration -> analyticsRepository.getDurationCorrelation();
            case time -> analyticsRepository.getTimeCorrelation();
            case obstacle -> analyticsRepository.getObstacleCorrelation();
            default -> throw new IllegalArgumentException("Only duration, time and distraction are supported");
        };
    }
}
