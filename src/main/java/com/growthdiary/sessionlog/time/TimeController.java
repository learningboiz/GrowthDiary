package com.growthdiary.sessionlog.time;

import com.growthdiary.sessionlog.feedback.Feedback;
import com.growthdiary.sessionlog.session.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.LocalTime;

@RestController
public class TimeController {

    private final TimeService timeService;

    @Autowired
    public TimeController(TimeService timeService) {
        this.timeService = timeService;
    }

    @PostMapping("/start")
    public ResponseEntity<String> startSession() {
        return ResponseEntity.ok("Session started");
    }

    @PostMapping("/end")
    public ResponseEntity<String> endSession() {
        return ResponseEntity.ok("Session ended");
    }

    /**
     * Handles the creation of Time object for a user's session
     * @return Time object
     */
    @PostMapping("/total-time")
    public ResponseEntity<Time> totalTime(@RequestParam LocalDate firstDate,
                                          @RequestParam LocalDate lastDate,
                                          @RequestParam LocalTime startTime,
                                          @RequestParam LocalTime endTime) {
        Time time = timeService.createTime(firstDate,lastDate, startTime, endTime);
        return new ResponseEntity<>(time, HttpStatus.CREATED);
    }
}
