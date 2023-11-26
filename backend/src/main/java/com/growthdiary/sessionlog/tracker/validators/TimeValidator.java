package com.growthdiary.sessionlog.tracker.validators;

import com.growthdiary.sessionlog.tracker.models.Time;
import jakarta.validation.constraints.NotNull;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

/**
 * This class is a Spring Validator implementation used to validate instances of the Time object
 * Checks validity of Time object attributes
 *
 * @see Time
 */
@Component
public class TimeValidator implements Validator {
    @Override
    public boolean supports(@NotNull Class clazz) {
        return Time.class.equals(clazz);
    }

    /**
     * Validates the given Time object
     * Checks for null-values and range constraints
     *
     * @param target Time object to be validated
     * @param errors Stores any validation errors encountered
     */
    @Override
    public void validate(@NotNull Object target, @NotNull Errors errors) {
        Time time = (Time) target;

        if (anyNullProperties(time)) {
            errors.reject("time.null", "Dates, times and duration must not be null");
        } else if (!validDuration(time)) {
            errors.rejectValue("duration", "duration.negative", "Duration must be a non-negative value");
        }
    }

    /*
     * Utility method to check if any attributes are null
     */
    private boolean anyNullProperties(Time time) {
        return time.getStartDate() == null || time.getStartTime() == null || time.getDuration() == null;
    }

    /*
     * Utility method used to verify whether the provided duration is consistent with the dates and times provided
     * Returns a boolean value to indicate whether duration is consistent
     */
    private boolean validDuration(Time time) {
        return time.getDuration() >= 0;
    }

}
