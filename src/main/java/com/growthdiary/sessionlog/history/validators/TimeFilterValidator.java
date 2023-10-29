package com.growthdiary.sessionlog.history.validators;

import com.growthdiary.sessionlog.history.historyfilter.FilterOperations;
import com.growthdiary.sessionlog.history.historyfilter.TimeFilter;
import jakarta.validation.constraints.NotNull;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

public class TimeFilterValidator implements Validator {

    @Override
    public boolean supports(@NotNull Class clazz) {
        return TimeFilter.class.equals(clazz);
    }

    /*
     * Validates the time filter
     *
     * Split the validation into various helper methods as there were many nested if/else statements
     * This helped to break up the complexity and provide more clarity
     *
     * Validation flow as follows:
     * 1. Validate that at least one FilterOperation is not null (since a valid filter with have at least one input)
     * 2. If operation exists, validate that it is one of the FilterOperation enums
     * 3. If the above validations work, validate the attribute (date, duration or both)
     * 4. For each attribute, validate that the primary value exists
     * 5. If attribute involves a range filter, validate that the secondary value exists
     * 6. If attribute involves a range filter, validate that the primary value comes before/less than secondary value
     * 7. For durations, validate that the values are positive since Long types can be negative
     */
    @Override
    public void validate(@NotNull Object target, @NotNull Errors errors) {

        TimeFilter timeFilter = (TimeFilter) target;

        FilterOperations dateOperation = timeFilter.getDateOperation();
        FilterOperations durationOperation = timeFilter.getDurationOperation();

        if (eitherOperationNotNull(dateOperation, durationOperation, errors)) {

            if (dateOperation != null && operationIsValid(dateOperation, "dateOperation", errors)) {
                validateDates(timeFilter, errors);
            }

            if (durationOperation != null && operationIsValid(durationOperation, "durationOperation", errors)) {
                validateDurations(timeFilter, errors);
            }
        }
    }

    /* ---------------------------- Private helper methods: Verify operations -------------------------------------- */

    private boolean eitherOperationNotNull(FilterOperations dateOperation, FilterOperations durationOperation, Errors errors) {
        if (dateOperation == null && durationOperation == null) {
            errors.reject("timeFilter.bothOperators.null", "TimeFilter must have one working operator");
            return false;
        } else {
            return true;
        }
    }

    private boolean operationIsValid(FilterOperations op, String fieldName, Errors errors) {
        List<FilterOperations> acceptableOperations = Arrays.asList(FilterOperations.EQUALS, FilterOperations.GREATER_THAN, FilterOperations.LESS_THAN, FilterOperations.BETWEEN);
        if (acceptableOperations.contains(op)) {
            return true;
        } else {
            errors.rejectValue(fieldName, fieldName + ".notAccepted", "The operation provided is not valid.");
            return false;
        }
    }

    /* ---------------------------- Private helper methods: Verify dates -------------------------------------- */

    private void validateDates(TimeFilter timeFilter, Errors errors) {
        String fieldName = "primaryDate";
        FilterOperations dateOperation = timeFilter.getDateOperation();
        LocalDate primaryDate = timeFilter.getPrimaryDate();
        LocalDate secondaryDate = timeFilter.getSecondaryDate();

        if (primaryDate == null) {
            errors.rejectValue(fieldName, fieldName + ".null", "Filtering by date requires a date value");
        } else if (dateOperation == FilterOperations.BETWEEN && secondaryDate == null) {
            errors.rejectValue(fieldName, fieldName + ".secondDate.null", "Filtering by a range requires two values");
        } else if (dateOperation == FilterOperations.BETWEEN && !firstDateBeforeSecond(primaryDate, secondaryDate)) {
            errors.rejectValue(fieldName, fieldName + ".secondDate.incorrectOrder", "Filtering by date range requires the first date to come before the second date");
        }
    }

    private boolean firstDateBeforeSecond(LocalDate firstDate, LocalDate secondDate) {
        return firstDate.isBefore(secondDate);
    }

    /* ---------------------------- Private helper methods: Verify durations -------------------------------------- */

    private void validateDurations(TimeFilter timeFilter, Errors errors) {
        String fieldName = "primaryDuration";
        FilterOperations durationOperation = timeFilter.getDurationOperation();
        Long primaryDuration = timeFilter.getPrimaryDuration();
        Long secondaryDuration = timeFilter.getSecondaryDuration();

        if (primaryDuration == null) {
            errors.rejectValue(fieldName, fieldName + ".null", "Filtering by duration requires a duration value");
        } else if (durationOperation == FilterOperations.BETWEEN && secondaryDuration == null) {
            errors.rejectValue(fieldName, fieldName + ".secondDuration.null", "Filtering by a range requires two values");
        } else if (!positiveDuration(primaryDuration)) {
            errors.rejectValue(fieldName, fieldName + ".negativeValue", "Duration must be a positive value");
        } else if (secondaryDuration != null && !positiveDuration(secondaryDuration)) {
            errors.rejectValue("secondaryDuration", "secondaryDuration.negativeValue","Duration must be a positive value" );
        } else if (durationOperation == FilterOperations.BETWEEN && !firstDurationLessThanSecond(primaryDuration, secondaryDuration)) {
            errors.rejectValue(fieldName, fieldName + ".secondDate.incorrectOrder", "Filtering by duration requires the first value to be less than the second");
        }
    }

    private boolean firstDurationLessThanSecond(Long firstDuration, Long secondDuration) {
        return firstDuration < secondDuration;
    }

    private boolean positiveDuration(Long duration) {
        return duration > 0;
    }
}
