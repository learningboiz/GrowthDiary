package com.growthdiary.sessionlog.history;

import com.growthdiary.sessionlog.history.dtos.HistoryViewDTO;
import com.growthdiary.sessionlog.history.dtos.SortRequestDTO;
import com.growthdiary.sessionlog.history.historysort.SortBuilder;
import com.growthdiary.sessionlog.history.historysort.SortDirection;
import com.growthdiary.sessionlog.history.specifications.*;
import com.growthdiary.sessionlog.history.dtos.FilterRequestDTO;
import com.growthdiary.sessionlog.history.validators.SortRequestDTOValidator;
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
    private final SortRequestDTOValidator sortRequestValidator;

    private final int defaultPageNum = 0;

    private final int defaultPageSize = 10;

    private final String defaultSortProperty = "time.startDate";

    private final SortDirection defaultSortDirection = SortDirection.DESC;

    @Autowired
    public HistoryService(HistoryRepository historyRepository, SortRequestDTOValidator sortRequestValidator) {
        this.historyRepository = historyRepository;
        this.sortRequestValidator = sortRequestValidator;
    }

    public Page<Session> getDefaultSessions(){

        Pageable pageable = PageRequest.of(defaultPageNum, defaultPageSize);
        return historyRepository.findAll(pageable);
    }


    public Page<Session> getRequestedSessions(FilterRequestDTO filterRequestDTO,
                                              HistoryViewDTO historyViewDTO,
                                              SortRequestDTO sortRequestDTO) {
        // TODO validate each DTO

        int pageNum = defaultPageNum;
        int pageSize = defaultPageSize;
        Sort sort = SortBuilder.buildSort(defaultSortProperty, defaultSortDirection);

        if (historyViewDTO != null) {
            pageNum = historyViewDTO.getPageNum();
            pageSize = historyViewDTO.getPageSize();
        }

        if (sortRequestDTO != null) {
            sort = SortBuilder.buildSort(sortRequestDTO.getProperty(), sortRequestDTO.getSortDirection());
        }

        Pageable pageable = PageRequest.of(pageNum, pageSize, sort);

        if (filterRequestDTO != null) {
            Specification<Session> sessionsFilter = SpecificationsMapper.getAllSpecifications(filterRequestDTO);
            return historyRepository.findAll(sessionsFilter, pageable);
        } else {
            return historyRepository.findAll(pageable);
        }
    }
}
