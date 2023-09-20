package com.growthdiary.sessionlog.session;

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

    @PostMapping("/complete")
    public ResponseEntity<Session> session(@RequestBody SessionDTO sessionDTO) {
        Session session = sessionService.createSession(sessionDTO);
        sessionService.saveSessionDetails(session);
        return new ResponseEntity<>(session, HttpStatus.CREATED);
    }
}
