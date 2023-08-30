package com.growthdiary.sessionlog.session;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@RestController
public class SessionController {

    private final SessionService sessionService;

    @Autowired
    public SessionController(SessionService sessionService) {
        this.sessionService = sessionService;
    }

    @PostMapping("/session")
    public ResponseEntity<Session> session(@RequestParam LocalDateTime startPeriod,
                                           @RequestParam LocalDateTime endPeriod,
                                           @RequestParam String topic,
                                           @RequestParam String category,
                                           @RequestParam Integer rating,
                                           @RequestParam String distraction,
                                           @RequestParam String emotion)
    {
        Session session = sessionService.createSession(
                startPeriod,
                endPeriod,
                topic,
                category,
                rating,
                distraction,
                emotion);

        return new ResponseEntity<>(session, HttpStatus.CREATED);
    }
}
