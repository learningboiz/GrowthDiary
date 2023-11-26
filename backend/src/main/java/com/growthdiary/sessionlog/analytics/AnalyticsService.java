package com.growthdiary.sessionlog.analytics;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
public class AnalyticsService {

    private final AnalyticsRepository analyticsRepository;

    @Autowired
    public AnalyticsService(AnalyticsRepository analyticsRepository) {
        this.analyticsRepository = analyticsRepository;
    }

    public WeeklySummary createWeeklySummary(LocalDate currentDate) {

        if (currentDate == null) {
            throw new IllegalArgumentException("Date cannot be null");
        } else {
            LocalDate dateOnMonday = findDateOnMonday(currentDate);
            Long rangeOfDays = ChronoUnit.DAYS.between(dateOnMonday, currentDate) + 1;
            return analyticsRepository.getSummary(dateOnMonday, currentDate, rangeOfDays);
        }
    }

    public List<ProductivityChart> createProductivityChart(ProductivityAttributes attribute) {
        return switch (attribute) {
            case duration -> analyticsRepository.getDurationCorrelation();
            case time -> analyticsRepository.getTimeCorrelation();
            case obstacle -> analyticsRepository.getObstacleCorrelation();
            default -> throw new IllegalArgumentException("Attribute not supported: Only duration, time and distraction are supported");
        };
    }

    private LocalDate findDateOnMonday(LocalDate currentDate) {

        DayOfWeek dayOfWeek = currentDate.getDayOfWeek();
        int daysSinceMonday = dayOfWeek.getValue() - 1;
        return currentDate.minusDays(daysSinceMonday);
    }
}
