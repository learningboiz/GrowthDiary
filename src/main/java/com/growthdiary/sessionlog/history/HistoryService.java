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

    @Autowired
    public HistoryService(HistoryRepository historyRepository,
                          SortRequestValidator sortRequestValidator,
                          FilterRequestValidator filterRequestValidator) {
        this.historyRepository = historyRepository;
        this.sortRequestValidator = sortRequestValidator;
        this.filterRequestValidator = filterRequestValidator;
    }

    public Page<Session> getDefaultSessions(){

        int defaultPageNum = 0;
        int defaultPageSize = 10;
        Sort defaultSort = SortBuilder.buildSort("time.startDate", SortDirection.DESC);

        Pageable pageable = PageRequest.of(defaultPageNum, defaultPageSize, defaultSort);
        return historyRepository.findAll(pageable);
    }

    /**
     * Retrieves a page of sessions based on the specified filter, page view, and sorting preferences.
     * Validates the input parameters and performs the retrieval according to the provided criteria.
     *
     * @param sessionHistoryDTO The data transfer object containing filter, page view, and sorting preferences.
     * @return A page of session records filtered and sorted as per the specified preferences.
     * @throws ValidationException If the filter input contain validation errors.
     * @throws IllegalArgumentException If the required page view or sorting preferences are missing.
     */
    public Page<Session> getRequestedSessions(SessionHistoryDTO sessionHistoryDTO) {

        FilterRequest filterRequest = sessionHistoryDTO.getFilterRequest();
        PageViewRequest pageViewRequest = sessionHistoryDTO.getPageViewRequest();
        SortRequest sortRequest = sessionHistoryDTO.getSortRequest();

        validateRequiredArguments(pageViewRequest,sortRequest);

        Errors errors = new BeanPropertyBindingResult(sessionHistoryDTO, "sessionHistoryDTO");
        validateSortRequest(sortRequest, errors);
        validateFilterRequest(filterRequest, errors);

        if (errors.hasErrors()) {
            throw new ValidationException(createErrorMessage(errors));

        } else {

            int pageNum = pageViewRequest.getPageNum();
            int pageSize = pageViewRequest.getPageSize();
            Sort sort = SortBuilder.buildSort(sortRequest.getProperty(), sortRequest.getSortDirection());

            Pageable pageable = PageRequest.of(pageNum, pageSize, sort);

            if (filterRequest != null) {
                Specification<Session> sessionsFilter = SpecificationsMapper.getAllSpecifications(filterRequest);
                return historyRepository.findAll(sessionsFilter, pageable);

            } else {
                return historyRepository.findAll(pageable);
            }
        }
    }

    /* ---------- Private helper methods: Validation and Error message creation  ------------------------------------ */
    private void validateRequiredArguments(PageViewRequest pageViewRequest, SortRequest sortRequest) {
        if (pageViewRequest == null || sortRequest == null) {
            throw new IllegalArgumentException("Both history view and sort preferences must be provided");
        }
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
