package com.growthdiary.sessionlog.analytics;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

@RestController
public class AnalyticsController {

    private final AnalyticsService analyticsService;

    @Autowired
    public AnalyticsController(AnalyticsService analyticsService) {
        this.analyticsService = analyticsService;
    }

    @GetMapping("/session/analytics")
    public ResponseEntity<WeeklySummary> getWeeklySummary(@RequestParam LocalDate currentDate) {
        return new ResponseEntity<>(analyticsService.createWeeklySummary(currentDate), HttpStatus.ACCEPTED);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    protected ResponseEntity<String> handleIllegalArgumentErrors(IllegalArgumentException exception) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(exception.getMessage());
    }
}
