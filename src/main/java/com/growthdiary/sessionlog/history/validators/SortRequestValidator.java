package com.growthdiary.sessionlog.history.validators;

import com.growthdiary.sessionlog.history.historysort.SortDirection;
import com.growthdiary.sessionlog.history.requests.SortRequest;
import jakarta.validation.constraints.NotNull;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.Arrays;
import java.util.List;

/**
 * This class is a Spring Validator implementation used to validate instances of the SortRequestDTO object
 * Checks validity of SortRequestDTO object attributes
 *
 * @see SortRequest
 */
@Component
public class SortRequestValidator implements Validator {

    private final List<String> validProperties = Arrays.asList("details.skill", "time.duration", "time.startDate", "feedback.productivity");
    private final List<SortDirection> validDirections = Arrays.asList(SortDirection.ASC, SortDirection.DESC);

    @Override
    public boolean supports(@NotNull Class clazz) {
        return SortRequest.class.equals(clazz);
    }

    /*
     * Validates the SortRequestDTO
     *
     * Split validation into two separate functions for clarity
     *
     * Validation flow as follows:
     * 1. Validate that a property has been specified and is within the validProperties list
     * 2. Validate that the direction has been specified and is within the validDirections list
     *
     */
    @Override
    public void validate(@NotNull Object target, @NotNull Errors errors) {

        SortRequest sortRequest = (SortRequest) target;

        validateProperty(sortRequest.getProperty(), errors);
        validateDirection(sortRequest.getSortDirection(), errors);
    }

    /* ----------------------------- Private helper methods ---------------------------------------------------- */

    private void validateProperty(String property, Errors errors) {
        String fieldName = "property";

        if (property == null) {
            errors.rejectValue(fieldName, fieldName + ".null", "Property to be sorted must not be null");
        } else if (!validProperties.contains(property)) {
            errors.rejectValue(fieldName, fieldName + ".invalid", "Only skill, duration, startDate and productivity can be sorted");
        }
    }

    private void validateDirection(SortDirection direction, Errors errors) {
        String fieldName = "sortDirection";

        if (direction == null) {
            errors.rejectValue(fieldName, fieldName + ".null", "Sort direction must not be null");
        } else if (!validDirections.contains(direction)) {
            errors.rejectValue(fieldName, fieldName + ".invalid", "Sort direction is either ASC or DESC");
        }
    }
}
