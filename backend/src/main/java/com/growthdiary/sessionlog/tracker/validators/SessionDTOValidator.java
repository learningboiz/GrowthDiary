package com.growthdiary.sessionlog.tracker.validators;

import com.growthdiary.sessionlog.tracker.SessionDTO;
import jakarta.validation.constraints.NotNull;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

/**
 * This class is a Spring Validator implementation used to validate instances of the SessionDTO object
 * Checks validity of SessionDTO object attributes
 *
 * @see SessionDTO
 */
@Component
public class SessionDTOValidator implements Validator {

    private final DetailsValidator detailsValidator;
    private final TimeValidator timeValidator;
    private final FeedbackValidator feedbackValidator;

    public SessionDTOValidator(DetailsValidator detailsValidator, TimeValidator timeValidator, FeedbackValidator feedbackValidator) {
        this.detailsValidator = detailsValidator;
        this.timeValidator = timeValidator;
        this.feedbackValidator = feedbackValidator;
    }

    @Override
    public boolean supports(@NotNull Class clazz) {
        return SessionDTO.class.equals(clazz);
    }

    /**
     * Validates the given SessionDTO object.
     * If any of the attributes are null, an error will be recorded and the method call ends.
     * If attributes are not null, their respective validators will be invoked for further validation.
     *
     * @param target SessionDTO object to be validated
     * @param errors Stores any validation errors encountered
     */
    @Override
    public void validate(@NotNull Object target, @NotNull Errors errors) {

        SessionDTO sessionDTO = (SessionDTO) target;

        validateNestedObject(sessionDTO.getDetails(), "details", detailsValidator, errors);
        validateNestedObject(sessionDTO.getTime(), "time", timeValidator, errors);
        validateNestedObject(sessionDTO.getFeedback(), "feedback", feedbackValidator, errors);
    }

    /*
     * Utility method used to check if the respective SessionDTO attributes are null
     * If attribute is null, the error will be recorded
     * If attribute is not null, the respective validator will be invoked to ensure that the attribute is valid
     */
    private void validateNestedObject(Object nestedObject, String fieldName, Validator validator, Errors errors) {
        if (nestedObject == null) {
            errors.rejectValue(fieldName, fieldName + ".null", fieldName + " must not be null");
        } else {
            errors.pushNestedPath(fieldName);
            ValidationUtils.invokeValidator(validator, nestedObject, errors);
            errors.popNestedPath();
        }
    }
}
