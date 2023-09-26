package com.growthdiary.sessionlog.history;

import com.growthdiary.sessionlog.history.specifications.*;
import com.growthdiary.sessionlog.tracker.session.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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

    public Page<Session> getSessions(HistoryDTO historyDTO) {

        Pageable pageable = PageRequest.of(historyDTO.getPageNum(), historyDTO.getPageSize(), historyDTO.getSort());

        if (historyDTO.getFilterRequest() == null) {
            return historyRepository.findAll(pageable);
        } else {
            List<FilterRequest> filterRequest = historyDTO.getFilterRequest();
            return historyRepository.findAll(dynamicFilter(filterRequest), pageable);
        }
    }

    public List<Session> getAllSessions() {
        return historyRepository.findAll();
    }

    private Specification<Session> dynamicFilter(List<FilterRequest> filterRequests) {

        Specification<Session> compiledSpecs = Specification.where(null);

        for (FilterRequest filter : filterRequests) {
            String entity = filter.getEntity();

            switch (entity) {
                case "details": {
                    Specification<Session> detailsSpec = DetailsSpecifications.createDetailsSpec(filter);
                    compiledSpecs = compiledSpecs.and(detailsSpec);
                }
                case "time": {
                    Specification<Session> timeSpec = TimeSpecifications.createTimeSpec(filter);
                    compiledSpecs = compiledSpecs.and(timeSpec);
                }
                case "feedback": {
                    Specification<Session> feedbackSpec = FeedbackSpecifications.createFeedbackSpec(filter);
                    compiledSpecs = compiledSpecs.and(feedbackSpec);
                }
            }
        }
        return compiledSpecs;
    }
}
