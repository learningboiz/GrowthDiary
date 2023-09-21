package com.growthdiary.sessionlog.sessionhistory;

import com.growthdiary.sessionlog.session.Session;
import com.growthdiary.sessionlog.session.SessionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class SessionHistoryService {

    private final SessionRepository sessionRepository;

    /** Injects sessionRepository bean into the Spring context
     *
     * This repository has all necessary data regarding user's session history
     */
    @Autowired
    public SessionHistoryService(SessionRepository sessionRepository) {
        this.sessionRepository = sessionRepository;
    }


    /** Creates a Sort object that specifies property to be sorted and order of sorting
     *
     * @param direction The order to sort results in either ASC or DESC
     * @param property The property to be sorted
     * @return Sort object with specified sort direction and property
     */
    public Sort createSort(SortDirection direction, String property) {
        if (direction == SortDirection.ASC) {
            return Sort.by(Sort.Direction.ASC, property);
        } else {
            return Sort.by(Sort.Direction.DESC, property);
        }
    }

    /** Creates a Page that displays user's session history
     *
     * @param currentPage The current page to be displayed
     * @param numItems The number of sessions to be viewed in a page
     * @param sort The sorting order to view specified categories
     * @return Page object with specified portion of session history
     */

    @Query (value = "SELECT s.start_period AS start, s.end_period AS end, s.duration AS duration, f.productivity AS rating, f.distractions AS distraction, f.emotions AS emotion, sk.topic AS topic, sk.category AS category FROM session AS s INNER JOIN feedback AS f ON s.feedback_id = f.id INNER JOIN skill AS sk ON s.skill_id = sk.id;")
    public Page<Session> getSessionHistory(int currentPage, int numItems, Sort sort) {
        Pageable customPage = PageRequest.of(currentPage, numItems, sort);
        return sessionRepository.findAll(customPage);
    }
}
