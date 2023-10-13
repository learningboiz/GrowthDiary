package com.growthdiary.sessionlog.history;

import com.growthdiary.sessionlog.history.filters.*;
import com.growthdiary.sessionlog.history.requests.FilterRequest;
import com.growthdiary.sessionlog.tracker.session.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

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
     * Applies only sort criteria if there are no filter requests
     * @param historyDTO Object containing user request regarding page numbers, page size, sorts and data filters
     * @return a Page of filtered sessions
     */
    public Page<Session> getRequestedSessions(HistoryDTO historyDTO) {

        int pageNum = historyDTO.getPageNum();
        int pageSize = historyDTO.getPageSize();
        Sort sort = historyDTO.getSort();
        FilterRequest filterRequest = historyDTO.getFilterRequest();

        pageSizeValidator(pageSize);

        Pageable pageable = PageRequest.of(pageNum, pageSize, sort);

        if (filterRequest != null) {
            Specification<Session> sessionsFilter = FilterMapper.getFilteredSessions(filterRequest);
            return historyRepository.findAll(sessionsFilter, pageable);
        } else {
            return historyRepository.findAll(pageable);
        }
    }

    private void pageSizeValidator(int pageSize) {
        long totalEntries = historyRepository.count();
        if (pageSize > totalEntries) {
            throw new IllegalArgumentException("Page size exceeds the number of existing entries");
        }
    }
}
