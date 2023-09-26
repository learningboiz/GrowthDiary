package com.growthdiary.sessionlog.history;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HistoryController {

    private final HistoryService sessionHistoryService;

    @Autowired
    public HistoryController(HistoryService sessionHistoryService) {
        this.sessionHistoryService = sessionHistoryService;
    }

}
