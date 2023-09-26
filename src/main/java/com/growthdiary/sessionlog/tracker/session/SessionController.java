package com.growthdiary.sessionlog.tracker.session;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class SessionController {

    private final SessionService sessionService;

    @Autowired
    public SessionController(SessionService sessionService) {
        this.sessionService = sessionService;
    }

    @PostMapping("/session")
    public ResponseEntity<Session> session(@RequestBody SessionDTO sessionDTO) {
        Session session = sessionService.createSession(sessionDTO);
        return new ResponseEntity<>(session, HttpStatus.CREATED);
    }
}
