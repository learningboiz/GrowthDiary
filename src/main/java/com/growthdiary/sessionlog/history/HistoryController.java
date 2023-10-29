package com.growthdiary.sessionlog.history;

import com.growthdiary.sessionlog.history.requests.SessionHistoryDTO;
import com.growthdiary.sessionlog.tracker.session.Session;
import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HistoryController {

    private final HistoryService sessionHistoryService;

    @Autowired
    public HistoryController(HistoryService sessionHistoryService) {
        this.sessionHistoryService = sessionHistoryService;
    }

    @GetMapping("/session/history")
    public ResponseEntity<Page<Session>> allSessionHistory() {
        return new ResponseEntity<>(sessionHistoryService.getDefaultSessions(), HttpStatus.ACCEPTED);
    }

    @GetMapping("/session/history/filter")
    public ResponseEntity<Page<Session>> requestedSessionHistory(@RequestBody SessionHistoryDTO sessionHistoryDTO) {
        return new ResponseEntity<>(sessionHistoryService.getRequestedSessions(sessionHistoryDTO), HttpStatus.ACCEPTED);
    }

    @ExceptionHandler(ValidationException.class)
    protected ResponseEntity<String> handleValidationErrors(ValidationException exception) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(exception.getMessage());
    }

    @ExceptionHandler(IllegalArgumentException.class)
    protected ResponseEntity<String> handleIllegalArgumentErrors(IllegalArgumentException exception) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(exception.getMessage());
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    protected ResponseEntity<String> handleUnreadableHttpErrors(HttpMessageNotReadableException exception) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(exception.getMessage());
    }
}
