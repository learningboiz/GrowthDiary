package com.growthdiary.sessionlog.history;

import com.growthdiary.sessionlog.session.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HistoryService {

    private final HistoryRepository historyRepository;


    /** Injects sessionRepository bean into the Spring context
     *
     * This repository has all necessary data regarding user's session history
     */
    @Autowired
    public HistoryService(HistoryRepository historyRepository) {
        this.historyRepository = historyRepository;
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

    public List<Session> getAllSessions() {
        Specification<Session> spec = HistoryFilterSpecs.joinTables();
        return historyRepository.findAll(spec);
    }

    /**
     * Creates a list of sessions with skills matching any of the provided skill names
     * @param skills List of skills to be matched with existing sessions in the database
     * @return a list of session objects
     */
    public List<Session> filterBySkill(List<String> skills) {
        Specification<Session> spec = HistoryFilterSpecs.skillIs(skills);
        return historyRepository.findAll(spec);
    }

    /**
     * Creates a list of sessions with descriptions matching the given keywords
     * @param keywords Keywords that will be matched against descriptions in the database
     * @return
     */
    public List<Session> filterByDescription(String keywords) {
        Specification<Session> spec = HistoryFilterSpecs.descriptionLike(keywords);
        return historyRepository.findAll(spec);
    }


}
