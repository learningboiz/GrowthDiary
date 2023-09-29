package com.growthdiary.sessionlog.history;

import com.growthdiary.sessionlog.history.filters.*;
import com.growthdiary.sessionlog.tracker.session.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HistoryService {

    private final HistoryRepository historyRepository;

    @Autowired
    public HistoryService(HistoryRepository historyRepository) {
        this.historyRepository = historyRepository;
    }

    /**
     * Creates a Page of sessions based on the provided default values
     * @return a Page of sessions
     */
    public Page<Session> getDefaultSessions(){

        int defaultPageNum = 0;
        int defaultPageSize = 10;

        Pageable pageable = PageRequest.of(defaultPageNum, defaultPageSize);
        return historyRepository.findAll(pageable);
    }

    /**
     * Creates a Page of session based on the user requested parameters
     * @param historyDTO Object containing user request regarding page numbers, page size, sorts and data filters
     * @return a Page of filtered session
     */
    public Page<Session> getRequestedSessions(HistoryDTO historyDTO) {

        Pageable pageable = PageRequest.of(historyDTO.getPageNum(), historyDTO.getPageSize(), historyDTO.getSort());

        if (historyDTO.getFilterRequest() == null) {
            return historyRepository.findAll(pageable);
        } else {
            List<FilterRequest> filterRequest = historyDTO.getFilterRequest();
            return historyRepository.findAll(getFilteredSessions(filterRequest), pageable);
        }
    }

    private Specification<Session> getFilteredSessions(List<FilterRequest> filterRequests) {

        Specification<Session> compiledSpecs = Specification.where(null);

        for (FilterRequest filter : filterRequests) {
            String entity = filter.getEntity();

            switch (entity) {
                case "details": {
                    Specification<Session> detailsSpec = DetailsFilter.buildWith(filter);
                    compiledSpecs = compiledSpecs.and(detailsSpec);
                }
                case "time": {
                    Specification<Session> timeSpec = TimeFilter.buildWith(filter);
                    compiledSpecs = compiledSpecs.and(timeSpec);
                }
                case "feedback": {
                    Specification<Session> feedbackSpec = FeedbackFilter.buildWith(filter);
                    compiledSpecs = compiledSpecs.and(feedbackSpec);
                }
            }
        }
        return compiledSpecs;
    }
}
