package com.growthdiary.sessionlog.sessionlog;

import com.growthdiary.sessionlog.session.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SessionLogController {

    private final SessionLogService sessionLogService;

    @Autowired
    public SessionLogController(SessionLogService sessionLogService) {
        this.sessionLogService = sessionLogService;
    }

    @GetMapping("/history")
    public ResponseEntity<Page<Session>> history(){
        Page<Session> history = sessionLogService.defaultDisplay();
        return new ResponseEntity<>(history, HttpStatus.ACCEPTED);
    }
}
