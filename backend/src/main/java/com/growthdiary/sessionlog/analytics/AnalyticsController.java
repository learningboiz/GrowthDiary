package com.growthdiary.sessionlog.analytics;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.time.LocalDate;
import java.util.List;

@CrossOrigin(origins = "http://localhost:5173")
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

    @GetMapping("/session/analytics/productivity")
    public ResponseEntity<List<ProductivityChart>> getProductivityChart(@RequestParam ProductivityCategory category) {
        return new ResponseEntity<>(analyticsService.createProductivityChart(category), HttpStatus.ACCEPTED);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    protected ResponseEntity<String> handleIllegalArgumentErrors(IllegalArgumentException exception) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(exception.getMessage());
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    protected ResponseEntity<String> handleArgumentMismatchErrors(MethodArgumentTypeMismatchException exception) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body("Accepted attributes are duration, time and obstacle");
    }
}
