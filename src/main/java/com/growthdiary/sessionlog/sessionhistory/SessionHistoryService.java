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


    /** The default view will present the first 10 sessions of the user's log */
    public Page<Session> defaultDisplay() {
        int currentPage = 0;
        int numItems = 10;
        String initialSort = "startDate";
        Pageable defaultPage = PageRequest.of(currentPage, numItems, Sort.by(initialSort));
        return sessionRepository.findAll(defaultPage);
    }

    /** The custom view will present 10, 20 or 30 sessions of the user's log */
    public Page<Session> customDisplay(int currentPage, int numItems) {
        Pageable customPage = PageRequest.of(currentPage, numItems);
        return sessionRepository.findAll(customPage);
    }
}
