package com.growthdiary.sessionlog.history;

import com.growthdiary.sessionlog.tracker.session.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<Page<Session>> requestedSessionHistory(@RequestBody HistoryDTO historyDTO) {
        return new ResponseEntity<>(sessionHistoryService.getRequestedSessions(historyDTO), HttpStatus.ACCEPTED);
    }
}
