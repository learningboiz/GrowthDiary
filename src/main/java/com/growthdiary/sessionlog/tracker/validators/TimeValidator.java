package com.growthdiary.sessionlog.tracker.validators;

import com.growthdiary.sessionlog.tracker.time.Time;
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
        } else if (illogicalDate(time)) {
            errors.reject("time.null", "Start date must come before end date");
        } else if (!consistentDuration(time)) {
            errors.rejectValue("duration", "duration.inconsistent", "Duration must be consistent with dates and time provided");
        }
    }

    /*
     * Utility method to check if any attributes are null
     */
    private boolean anyNullProperties(Time time) {
        return time.getStartDate() == null || time.getStartTime() == null || time.getEndDate() == null ||
                time.getEndTime() == null || time.getDuration() == null;
    }

    /*
     * Utility method to ensure that startDate comes before endDate
     */
    private boolean illogicalDate(Time time) {
        LocalDate startDate = time.getStartDate();
        LocalDate endDate = time.getEndDate();
        return startDate.isAfter(endDate);
    }

    /*
     * Utility method used to verify whether the provided duration is consistent with the dates and times provided
     * Returns a boolean value to indicate whether duration is consistent
     */
    private boolean consistentDuration(Time time) {
        LocalDateTime startPeriod = time.getStartDate().atTime(time.getStartTime());
        LocalDateTime endPeriod = time.getEndDate().atTime(time.getEndTime());

        Long actualDuration = time.getDuration();
        Long expectedDuration = ChronoUnit.MINUTES.between(startPeriod, endPeriod);
        return expectedDuration.equals(actualDuration);
    }




}
