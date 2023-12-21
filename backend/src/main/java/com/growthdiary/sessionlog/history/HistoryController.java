package com.growthdiary.sessionlog.history;

import com.growthdiary.sessionlog.history.requests.SessionHistoryDTO;
import com.growthdiary.sessionlog.tracker.models.Session;
import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
public class HistoryController {

    private final HistoryService sessionHistoryService;

    @Autowired
    public HistoryController(HistoryService sessionHistoryService) {
        this.sessionHistoryService = sessionHistoryService;
    }

    @GetMapping("/session/history")
    public ResponseEntity<Page<Session>> getAllSessionHistory() {
        return new ResponseEntity<>(sessionHistoryService.getDefaultSessions(), HttpStatus.ACCEPTED);
    }

    /*
     * https://stackoverflow.com/questions/5020704/how-to-design-restful-search-filtering
     * Referred to the above thread to justify use of post instead of get
     * In short, post can be used to create a search - which is more appropriate especially when using complex request body
     */
    @PostMapping("/session/history/search")
    public ResponseEntity<Page<Session>> getRequestedSessionHistory(@RequestBody SessionHistoryDTO sessionHistoryDTO) {
        return new ResponseEntity<>(sessionHistoryService.getCustomSessions(sessionHistoryDTO), HttpStatus.ACCEPTED);
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
