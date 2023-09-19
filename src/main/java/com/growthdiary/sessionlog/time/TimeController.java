package com.growthdiary.sessionlog.time;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.LocalTime;

@RestController
@RequestMapping("/session")
public class TimeController {

    private final TimeService timeService;

    @Autowired
    public TimeController(TimeService timeService) {
        this.timeService = timeService;
    }

    /**
     * Handles the creation of Time object for a user's session
     * @return Time object
     */
    @PostMapping("/time")
    public ResponseEntity<Time> totalTime(@RequestParam LocalDate startDate,
                                          @RequestParam LocalDate endDate,
                                          @RequestParam LocalTime startTime,
                                          @RequestParam LocalTime endTime) {
        Time time = timeService.createTime(startDate, endDate, startTime, endTime);
        return new ResponseEntity<>(time, HttpStatus.CREATED);
    }
}
