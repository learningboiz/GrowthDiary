package com.growthdiary.sessionlog.history.validators;

import com.growthdiary.sessionlog.history.dtos.FilterRequestDTO;
import com.growthdiary.sessionlog.history.historyfilter.DetailsFilter;
import com.growthdiary.sessionlog.history.historyfilter.FeedbackFilter;
import com.growthdiary.sessionlog.history.historyfilter.TimeFilter;
import jakarta.validation.constraints.NotNull;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class FilterRequestDTOValidator implements Validator {

    private final DetailsFilterValidator detailsFilterValidator = new DetailsFilterValidator();
    private final TimeFilterValidator timeFilterValidator = new TimeFilterValidator();
    private final FeedbackFilterValidator feedbackFilterValidator = new FeedbackFilterValidator();

    @Override
    public boolean supports(@NotNull Class clazz) {
        return FilterRequestDTO.class.equals(clazz);
    }

    @Override
    public void validate(@NotNull Object target, @NotNull Errors errors) {

        FilterRequestDTO filterRequestDTO = (FilterRequestDTO) target;

        DetailsFilter detailsFilter = filterRequestDTO.getDetailsFilter();
        TimeFilter timeFilter = filterRequestDTO.getTimeFilter();
        FeedbackFilter feedbackFilter = filterRequestDTO.getFeedbackFilter();

        validateNestedFilter(detailsFilter, "detailsFilter", detailsFilterValidator, errors);
        validateNestedFilter(timeFilter, "timeFilter", timeFilterValidator, errors);
        validateNestedFilter(feedbackFilter, "feedbackFilter", feedbackFilterValidator, errors);
    }

    private void validateNestedFilter(Object nestedFilter, String nestedPath, Validator validator, Errors errors) {
        if (nestedFilter != null) {
            errors.pushNestedPath(nestedPath);
            ValidationUtils.invokeValidator(validator, nestedFilter, errors);
            errors.popNestedPath();
        }

    }
}
