package com.growthdiary.sessionlog.sessionhistory;

import com.growthdiary.sessionlog.session.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class SessionHistoryService {

    private final SessionRepository sessionRepository;

    @Autowired
    public SessionHistoryService(SessionRepository sessionRepository) {
        this.sessionRepository = sessionRepository;
    }

    public Page<Session> defaultDisplay() {
        int startPage = 0;
        int defaultDisplay = 10;
        String defaultArrangement = "startDate";
        Pageable firstPage = PageRequest.of(startPage, defaultDisplay, Sort.by(defaultArrangement));
        return sessionRepository.findAll(firstPage);
    }
}
