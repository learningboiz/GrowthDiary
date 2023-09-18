package com.growthdiary.sessionlog.session;

import com.growthdiary.sessionlog.feedback.Feedback;
import com.growthdiary.sessionlog.time.Time;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/session")
public class SessionController {

    private final SessionService sessionService;

    @Autowired
    public SessionController(SessionService sessionService) {
        this.sessionService = sessionService;
    }

    @PostMapping("/details")
    public ResponseEntity<String> getDetails() {
        return ResponseEntity.ok("Session started");
    }

    @PostMapping("/complete")
    public ResponseEntity<Session> session(@RequestParam String skill,
                                           @RequestParam String description,
                                           @RequestBody Time time,
                                           @RequestBody Feedback feedback) {
        Session session = sessionService.createSession(skill, description, time, feedback);
        return new ResponseEntity<>(session, HttpStatus.CREATED);
    }
}
