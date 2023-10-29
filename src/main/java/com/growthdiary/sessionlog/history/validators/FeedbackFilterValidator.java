package com.growthdiary.sessionlog.history.validators;

import com.growthdiary.sessionlog.history.historyfilter.FeedbackFilter;
import com.growthdiary.sessionlog.history.historyfilter.FilterOperations;
import jakarta.validation.constraints.NotNull;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.List;

public class FeedbackFilterValidator implements Validator  {
    @Override
    public boolean supports(@NotNull Class clazz) {
        return FeedbackFilter.class.equals(clazz);
    }

    /*
     * Validates the feedback filter
     *
     * Split the validation into various helper methods as there were many nested if/else statements
     * This helped to break up the complexity and provide more clarity
     *
     * Validation flow as follows:
     * 1. Validate that at least one attribute (distraction or productivity) is not null
     * 2. If productivityOperation exists (i.e. productivity has been filtered), validate the productivity values
     * 3. Validate that the primary productivity value is present
     * 4. If range is involved, validate that the secondary value exists
     * 5. If range is involved, validate that the primary value is less than the secondary value
     * 6. Validate the productivity values are both within the given range
     */
    @Override
    public void validate(@NotNull Object target, @NotNull Errors errors) {

        FeedbackFilter feedbackFilter = (FeedbackFilter) target;

        List<String> distractions = feedbackFilter.getDistractions();
        FilterOperations productivityOperations = feedbackFilter.getProductivityOperation();

        if ((distractions == null || distractions.isEmpty()) && productivityOperations == null) {
            errors.reject("feedbackFilter.attributes.null", "Feedback filter must specify distractions, productivity or both");
        } else if (productivityOperations != null) {
            validateProductivity(feedbackFilter, errors);
        }
    }

    /* ---------------------------- Private helper methods: Verify productivity -------------------------------------- */

    private void validateProductivity(FeedbackFilter feedbackFilter, Errors errors) {
        String fieldName = "primaryProductivity";
        FilterOperations productivityOperation = feedbackFilter.getProductivityOperation();
        Integer primaryProductivity = feedbackFilter.getPrimaryProductivity();
        Integer secondaryProductivity = feedbackFilter.getSecondaryProductivity();

        if (primaryProductivity == null) {
            errors.rejectValue(fieldName, fieldName + ".null", "Filtering by productivity requires a productivity value");
        } else if (productivityOperation == FilterOperations.BETWEEN && secondaryProductivity == null) {
            errors.rejectValue(fieldName, fieldName + ".secondaryProductivity.null", "Filtering by a range requires two values");
        } else if (!isInRange(primaryProductivity)) {
            errors.rejectValue(fieldName, fieldName + ".negativeValue", "Productivity must be within a range of 1 and 5 inclusive");
        } else if (secondaryProductivity != null && !isInRange(secondaryProductivity)) {
            errors.rejectValue("secondaryProductivity", "secondaryProductivity.negativeValue","Productivity must be within a range of 1 and 5 inclusive");
        } else if (productivityOperation == FilterOperations.BETWEEN && !firstValueLessThanSecond(primaryProductivity, secondaryProductivity)) {
            errors.rejectValue(fieldName, fieldName + ".secondProductivity.incorrectOrder", "Filtering by productivity requires the first value to be less than the second");
        }
    }

    private boolean firstValueLessThanSecond(Integer firstValue, Integer secondValue) {
        return firstValue < secondValue;
    }

    private boolean isInRange(Integer productivity) {
        int min = 1;
        int max = 5;
        return productivity >= min && productivity <= max;
    }
}
