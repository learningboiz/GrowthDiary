package com.growthdiary.sessionlog.tracker.validators;

import com.growthdiary.sessionlog.tracker.models.Feedback;
import jakarta.validation.constraints.NotNull;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 * This class is a Spring Validator implementation used to validate instances of the Feedback object
 * Checks validity of Feedback object attributes
 *
 * @see Feedback
 */
@Component
public class FeedbackValidator implements Validator {
    @Override
    public boolean supports(@NotNull Class clazz) {
        return Feedback.class.equals(clazz);
    }

    /**
     * Validates the given Feedback object
     * Checks for null-values and range constraints
     *
     * @param target Feedback object to be validated
     * @param errors Stores any validation errors encountered
     */
    @Override
    public void validate(@NotNull Object target, @NotNull Errors errors) {

        Feedback feedback = (Feedback) target;

        if (feedback.getDistraction() == null) {
            errors.rejectValue("distraction", "distraction.null", "Distraction must not be empty");
        }

        if (feedback.getProductivity() == null) {
            errors.rejectValue("productivity", "productivity.null", "Productivity must not be null");
        } else if (!withinRange(feedback.getProductivity())) {
            errors.rejectValue("productivity", "productivity.outside.range", "Productivity must be between 1 and 5 inclusive");
            }
        }

    /*
     * Utility method used to verify whether the productivity value is within the specified range
     */
    private boolean withinRange(int value) {
        int min = 1;
        int max = 5;
        return value >= min && value <= max;
    }
}
