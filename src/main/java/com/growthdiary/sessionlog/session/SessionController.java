package com.growthdiary.sessionlog.session;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.LocalTime;

@RestController
public class SessionController {

    private final SessionService sessionService;

    @Autowired
    public SessionController(SessionService sessionService) {
        this.sessionService = sessionService;
    }

    @PostMapping("/session")
    public ResponseEntity<Session> session(@RequestParam LocalDate startDate,
                                           @RequestParam LocalDate endDate,
                                           @RequestParam LocalTime startTime,
                                           @RequestParam LocalTime endTime,
                                           @RequestParam String topic,
                                           @RequestParam String category,
                                           @RequestParam Integer rating,
                                           @RequestParam String distraction,
                                           @RequestParam String emotion)
    {
        Session session = sessionService.createSession(
                startDate,
                endDate,
                startTime,
                endTime,
                topic,
                category,
                rating,
                distraction,
                emotion);

        return new ResponseEntity<>(session, HttpStatus.CREATED);
    }
}