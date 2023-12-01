package com.growthdiary.sessionlog.tracker;

import com.growthdiary.sessionlog.tracker.models.Session;
import jakarta.validation.Valid;
import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:5174")
@RestController
public class SessionController {

    private final SessionService sessionService;

    @Autowired
    public SessionController(SessionService sessionService) {
        this.sessionService = sessionService;
    }

    @PostMapping(value = "/session", consumes = "application/json")
    public ResponseEntity<Session> createSession(@RequestBody SessionDTO sessionDTO) {
        Session session = sessionService.createSession(sessionDTO);
        return new ResponseEntity<>(session, HttpStatus.CREATED);
    }

    @ExceptionHandler(ValidationException.class)
    protected ResponseEntity<String> handleInvalidSessionInput(ValidationException exception) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(exception.getMessage());
    }
}
