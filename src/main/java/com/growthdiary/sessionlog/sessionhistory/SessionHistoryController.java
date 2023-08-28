package com.growthdiary.sessionlog.sessionhistory;

import com.growthdiary.sessionlog.session.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SessionHistoryController {

    private final SessionHistoryService sessionLogService;

    @Autowired
    public SessionHistoryController(SessionHistoryService sessionLogService) {
        this.sessionLogService = sessionLogService;
    }

    @GetMapping("/history")
    public ResponseEntity<Page<Session>> history(){
        Page<Session> history = sessionLogService.defaultDisplay();
        return new ResponseEntity<>(history, HttpStatus.ACCEPTED);
    }
}
