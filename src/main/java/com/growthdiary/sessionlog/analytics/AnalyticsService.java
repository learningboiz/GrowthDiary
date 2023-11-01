package com.growthdiary.sessionlog.analytics;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

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

    private LocalDate findDateOnMonday(LocalDate currentDate) {

        DayOfWeek dayOfWeek = currentDate.getDayOfWeek();
        int daysSinceMonday = dayOfWeek.getValue() - 1;
        return currentDate.minusDays(daysSinceMonday);
    }
}
