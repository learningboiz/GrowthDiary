package com.growthdiary.sessionlog.sessionhistory;

import com.growthdiary.sessionlog.session.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SessionHistoryController {

    private final SessionHistoryService sessionHistoryService;

    @Autowired
    public SessionHistoryController(SessionHistoryService sessionHistoryService) {
        this.sessionHistoryService = sessionHistoryService;
    }

    @GetMapping("/history")
    @Validated
    public ResponseEntity<Page<Session>> customHistoryDisplay(@RequestParam(defaultValue = "0") int currentPage,
                                                              @RequestParam(defaultValue = "5") int numItems){
        Page<Session> customDisplay = sessionHistoryService.getSessionHistory(currentPage, numItems);
        return  new ResponseEntity<>(customDisplay, HttpStatus.ACCEPTED);
    }
}
