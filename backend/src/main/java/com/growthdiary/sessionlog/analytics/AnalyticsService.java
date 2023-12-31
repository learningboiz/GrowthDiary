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

    public WeeklySummary createWeeklySummary(LocalDate currentDate) {

        Long daysAgo = 7L;
        Integer maxProductivity = 5;

        if (currentDate == null) {
            throw new IllegalArgumentException("Date cannot be null");
        } else {
            LocalDate minRange = getDateDaysAgo(currentDate, daysAgo);
            return analyticsRepository.getSummary(minRange, currentDate, daysAgo, maxProductivity);
        }
    }

    public List<ProductivityChart> createProductivityChart(ProductivityCategory category) {
        return switch (category) {
            case duration -> analyticsRepository.getDurationCorrelation();
            case time -> analyticsRepository.getTimeCorrelation();
            case obstacle -> analyticsRepository.getObstacleCorrelation();
            default -> throw new IllegalArgumentException("Only duration, time and distraction are supported");
        };
    }

    private LocalDate getDateDaysAgo(LocalDate currentDate, Long daysAgo) {

        return currentDate.minusDays(daysAgo);
    }
}
