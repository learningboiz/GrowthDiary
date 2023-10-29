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

        if (oneOrMoreOperationsSpecified(dateOperation, durationOperation, errors)) {

            if (dateOperation != null && operationIsValid(dateOperation, "dateOperation", errors)) {
                validateDates(timeFilter, errors);
            }

            if (durationOperation != null && operationIsValid(durationOperation, "durationOperation", errors)) {
                validateDurations(timeFilter, errors);
            }
        }
    }

    /* ---------------------------- Private helper methods: Verify operations -------------------------------------- */

    // TODO might be useful to find away to abstract away the operator checks

    private boolean oneOrMoreOperationsSpecified(FilterOperations dateOperation, FilterOperations durationOperation, Errors errors) {
        if (dateOperation == null && durationOperation == null) {
            errors.reject("timeFilter.bothOperators.null", "Missing value(s): Time filter must specify operations");
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

        if (dateOperation == FilterOperations.BETWEEN) {

            if (primaryDate == null || secondaryDate == null) {
                errors.rejectValue(fieldName, fieldName + ".secondValueNull", "Missing value(s): Date range filter requires two values");

            } else if (!firstDateBeforeSecond(primaryDate, secondaryDate)) {
                errors.rejectValue(fieldName, fieldName + ".incorrectOrderOfValues", "Incorrect order: First date must be earlier than the second date");
            }

        } else if (primaryDate == null) {
            errors.rejectValue(fieldName, fieldName + ".null", "Missing value: Date filter requires a date value");
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

        if (durationOperation == FilterOperations.BETWEEN) {

            if (primaryDuration == null || secondaryDuration == null) {
                errors.rejectValue(fieldName, fieldName + ".secondValueNull", "Missing value(s): Duration range filter requires two values");

            } else if (!positiveDuration(primaryDuration) || !positiveDuration(secondaryDuration)) {
                errors.rejectValue(fieldName, fieldName + ".negativeValue", "Negative value(s): Duration values must be positive");

            } else if (!firstDurationLessThanSecond(primaryDuration, secondaryDuration)) {
                errors.rejectValue(fieldName, fieldName + ".incorrectOrderOfValues", "Incorrect order: First duration must be shorter than the second");
            }

        } else if (primaryDuration == null || !positiveDuration(primaryDuration)) {
            errors.rejectValue(fieldName, fieldName + ".nullOrNegative", "Missing value: Duration filter requires a positive duration value");
        }
    }

    private boolean firstDurationLessThanSecond(Long firstDuration, Long secondDuration) {
        return firstDuration < secondDuration;
    }

    private boolean positiveDuration(Long duration) {
        return duration > 0;
    }
}
