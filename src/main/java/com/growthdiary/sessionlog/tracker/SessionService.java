package com.growthdiary.sessionlog.tracker;

import com.growthdiary.sessionlog.tracker.models.Details;
import com.growthdiary.sessionlog.tracker.models.Feedback;
import com.growthdiary.sessionlog.tracker.models.Session;
import com.growthdiary.sessionlog.tracker.models.Time;
import com.growthdiary.sessionlog.tracker.validators.SessionDTOValidator;
import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;


/**
 * Service class in-charge of validating the user's Session data, creating a Session object and persisting it into the database
 */
@Service
public class SessionService {

    private final SessionRepository sessionRepository;

    private final SessionDTOValidator sessionDTOValidator;

    /**
     * Injects a sessionRepository object into the Spring context
     */
    @Autowired
    public SessionService(SessionRepository sessionRepository,
                          SessionDTOValidator sessionDTOValidator) {
        this.sessionRepository = sessionRepository;
        this.sessionDTOValidator = sessionDTOValidator;
    }

    /**
     * Validates the SessionDTO. Creates and persists data if the validation is successful.
     *
     * @param sessionDTO SessionDTO that wraps the details, time and feedback objects
     * @return Session object - Validated Session object with all required attributes
     * @throws ValidationException ValidationException thrown if user input is invalid
     */
    public Session createSession(SessionDTO sessionDTO) {

        Errors errors = new BeanPropertyBindingResult(sessionDTO, "sessionDTO");
        sessionDTOValidator.validate(sessionDTO, errors);

        if (errors.hasErrors()) {
            throw new ValidationException(createErrorMessage(errors));

        } else {
            Details details = sessionDTO.getDetails();
            Time time = sessionDTO.getTime();
            Feedback feedback = sessionDTO.getFeedback();

            Session session = new Session(details, time, feedback);
            return sessionRepository.save(session);
        }
    }

    /*
     * Helper method to build an error message based on all the field errors returned by the Errors object.
     * Formats error to improve readability and clarity for the user
     */
    private String createErrorMessage(Errors errors) {
        StringBuilder errorBuilder = new StringBuilder("Validation failed. Reported error(s): ");
        for (ObjectError objectError : errors.getAllErrors()) {
            errorBuilder.append(objectError.getDefaultMessage()).append("; ");
        }
        return errorBuilder.toString();
    }
}
