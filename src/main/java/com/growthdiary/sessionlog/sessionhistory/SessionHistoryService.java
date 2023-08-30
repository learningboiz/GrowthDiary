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

    /** Inject the sessionRepository bean into the Spring context since only one instance is required
     *
     * The bean instance will be solely responsible for interactions with the database
     */
    @Autowired
    public SessionHistoryService(SessionRepository sessionRepository) {
        this.sessionRepository = sessionRepository;
    }

    /** Creates an object to display user's session history by retrieving specified data from database
     *
     * @param currentPage the current page to be displayed
     * @param numItems the number of sessions to be displayed within the specified page
     * @return Page object with specified portion of session history
     */
    public Page<Session> getSessionHistory(int currentPage, int numItems) {
        Pageable customPage = PageRequest.of(currentPage, numItems);
        return sessionRepository.findAll(customPage);
    }
}
