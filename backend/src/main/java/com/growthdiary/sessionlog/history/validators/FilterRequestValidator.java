package com.growthdiary.sessionlog.history.validators;

import com.growthdiary.sessionlog.history.requests.FilterRequest;
import com.growthdiary.sessionlog.history.historyfilter.DetailsFilter;
import com.growthdiary.sessionlog.history.historyfilter.FeedbackFilter;
import com.growthdiary.sessionlog.history.historyfilter.TimeFilter;
import jakarta.validation.constraints.NotNull;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class FilterRequestValidator implements Validator {

    private final DetailsFilterValidator detailsFilterValidator = new DetailsFilterValidator();
    private final TimeFilterValidator timeFilterValidator = new TimeFilterValidator();
    private final FeedbackFilterValidator feedbackFilterValidator = new FeedbackFilterValidator();

    @Override
    public boolean supports(@NotNull Class clazz) {
        return FilterRequest.class.equals(clazz);
    }

    @Override
    public void validate(@NotNull Object target, @NotNull Errors errors) {

        FilterRequest filterRequest = (FilterRequest) target;

        DetailsFilter detailsFilter = filterRequest.getDetailsFilter();
        TimeFilter timeFilter = filterRequest.getTimeFilter();
        FeedbackFilter feedbackFilter = filterRequest.getFeedbackFilter();

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
