package com.growthdiary.sessionlog.tracker.session;

import jakarta.validation.Valid;
import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@Validated
public class SessionController {

    private final SessionService sessionService;

    @Autowired
    public SessionController(SessionService sessionService) {
        this.sessionService = sessionService;
    }

    @PostMapping("/session")
    public ResponseEntity<Session> session(@Valid @RequestBody SessionDTO sessionDTO) {
        Session session = sessionService.createSession(sessionDTO);
        return new ResponseEntity<>(session, HttpStatus.CREATED);
    }

    @ExceptionHandler(ValidationException.class)
    protected ResponseEntity<String> invalidSessionHandler(ValidationException exception) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(exception.getMessage());
    }
}
