package com.growthdiary.sessionlog.history;

import com.growthdiary.sessionlog.history.filters.*;
import com.growthdiary.sessionlog.history.requests.FilterRequest;
import com.growthdiary.sessionlog.tracker.session.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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
     * Creates a Page of sessions based on the user requested parameters
     * Applies requested filters and sort criteria if both are present
     * Applies only sort criteria if there are not filter requests
     * @param historyDTO Object containing user request regarding page numbers, page size, sorts and data filters
     * @return a Page of filtered sessions
     */
    public Page<Session> getRequestedSessions(HistoryDTO historyDTO) {

        Pageable pageable = PageRequest.of(historyDTO.getPageNum(), historyDTO.getPageSize(), historyDTO.getSort());

        if (historyDTO.getFilterRequest() == null) {
            return historyRepository.findAll(pageable);
        } else {
            List<FilterRequest> filterRequest = historyDTO.getFilterRequest();
            return historyRepository.findAll(FilterMapper.getFilteredSessions(filterRequest), pageable);
        }
    }
}
