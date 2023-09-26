package com.growthdiary.sessionlog.history;

import com.growthdiary.sessionlog.history.specifications.*;
import com.growthdiary.sessionlog.session.Session;
import org.springframework.beans.factory.annotation.Autowired;
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
        return historyRepository.findAll();
    }

    public List<Session> dynamicFilter(List<FilterRequest> filterRequests) {

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
        return historyRepository.findAll(compiledSpecs);
    }

}
