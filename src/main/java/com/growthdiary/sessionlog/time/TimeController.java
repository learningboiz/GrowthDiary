package com.growthdiary.sessionlog.time;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

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
    public ResponseEntity<Time> totalTime(@RequestParam LocalDateTime startPeriod,
                                          @RequestParam LocalDateTime endPeriod) {
        Time time = timeService.createTime(startPeriod, endPeriod);
        return new ResponseEntity<>(time, HttpStatus.CREATED);
    }
}
