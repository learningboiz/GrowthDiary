package com.growthdiary.sessionlog.history;

import com.growthdiary.sessionlog.history.requests.PageViewRequest;
import com.growthdiary.sessionlog.history.requests.SessionHistoryDTO;
import com.growthdiary.sessionlog.history.requests.SortRequest;
import com.growthdiary.sessionlog.history.historysort.SortBuilder;
import com.growthdiary.sessionlog.history.historysort.SortDirection;
import com.growthdiary.sessionlog.history.specifications.*;
import com.growthdiary.sessionlog.history.requests.FilterRequest;
import com.growthdiary.sessionlog.history.validators.FilterRequestValidator;
import com.growthdiary.sessionlog.history.validators.SortRequestValidator;
import com.growthdiary.sessionlog.tracker.models.Session;
import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;

/**
 * The {@code HistoryService} class manages the retrieval of sessions based on specified criteria.
 * It provides a default sessions display and takes in filters, sorting and page view requests for a customised display.
 */
@Service
public class HistoryService {

    private final HistoryRepository historyRepository;
    private final SortRequestValidator sortRequestValidator;
    private final FilterRequestValidator filterRequestValidator;

    private final int DEFAULT_PAGE_NUM = 0;
    private final int DEFAULT_PAGE_SIZE = 10;

    private final Sort DEFAULT_SORT = SortBuilder.buildSort("time.startDate", SortDirection.DESC);

    @Autowired
    public HistoryService(HistoryRepository historyRepository,
                          SortRequestValidator sortRequestValidator,
                          FilterRequestValidator filterRequestValidator) {
        this.historyRepository = historyRepository;
        this.sortRequestValidator = sortRequestValidator;
        this.filterRequestValidator = filterRequestValidator;
    }

    public Page<Session> getDefaultSessions(){
        return historyRepository.findAll(createDefaultPageable());
    }



    public Page<Session> getCustomSessions(SessionHistoryDTO sessionHistoryDTO) {

        FilterRequest filterRequest = sessionHistoryDTO.getFilterRequest();
        PageViewRequest pageViewRequest = sessionHistoryDTO.getPageViewRequest();
        SortRequest sortRequest = sessionHistoryDTO.getSortRequest();

        Errors errors = new BeanPropertyBindingResult(sessionHistoryDTO, "sessionHistoryDTO");
        validateSortRequest(sortRequest, errors);
        validateFilterRequest(filterRequest, errors);

        if (errors.hasErrors()) {
            throw new ValidationException(createErrorMessage(errors));

        } else {

            Pageable pageable = createCustomPageable(sessionHistoryDTO);

            if (filterRequest != null) {
                Specification<Session> sessionsFilter = SpecificationsMapper.getAllSpecifications(filterRequest);
                return historyRepository.findAll(sessionsFilter, pageable);

            } else {
                return historyRepository.findAll(pageable);
            }
        }
    }

    /* ---------- Private helper methods: Validation and Error message creation  ------------------------------------ */

    private Pageable createDefaultPageable() {
        return PageRequest.of(DEFAULT_PAGE_NUM, DEFAULT_PAGE_SIZE, DEFAULT_SORT);
    }

    private Pageable createCustomPageable(SessionHistoryDTO sessionHistoryDTO) {

        // all values initialised to default in case custom values are null
        // provides flexibility for users to submit filter-only requests without changing page-view/sort
        int customPageNum = DEFAULT_PAGE_NUM;
        int customPageSize = DEFAULT_PAGE_SIZE;
        Sort customSort = DEFAULT_SORT;

        PageViewRequest pageViewRequest = sessionHistoryDTO.getPageViewRequest();
        SortRequest sortRequest = sessionHistoryDTO.getSortRequest();

        if (pageViewRequest != null) {

            Integer providedPageIndex = pageViewRequest.getPageIndex();
            Integer providedPageSize = pageViewRequest.getPageSize();

            if (providedPageIndex != null) {
                customPageNum = providedPageIndex;
            }

            if (providedPageSize != null) {
                customPageSize = providedPageSize;
            }
        }

        if (sortRequest != null) {
            customSort = SortBuilder.buildSort(sortRequest.getProperty(), sortRequest.getSortDirection());
        }

        return PageRequest.of(customPageNum, customPageSize, customSort);
    }

    private void validateSortRequest(SortRequest sortRequest, Errors errors) {
        errors.pushNestedPath("sortRequest");
        sortRequestValidator.validate(sortRequest, errors);
        errors.popNestedPath();
    }

    private void validateFilterRequest(FilterRequest filterRequest, Errors errors) {
        if (filterRequest != null) {
            errors.pushNestedPath("filterRequest");
            filterRequestValidator.validate(filterRequest, errors);
            errors.popNestedPath();
        }
    }

    private String createErrorMessage(Errors errors) {
        StringBuilder errorBuilder = new StringBuilder("Validation failed. Reported error(s): ");
        for (ObjectError objectError : errors.getAllErrors()) {
            errorBuilder.append(objectError.getDefaultMessage()).append("; ");
        }
        return errorBuilder.toString();
    }
}
