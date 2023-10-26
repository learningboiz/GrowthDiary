package com.growthdiary.sessionlog.tracker.session;

import com.growthdiary.sessionlog.tracker.details.Details;
import com.growthdiary.sessionlog.tracker.details.DetailsRepository;
import com.growthdiary.sessionlog.tracker.feedback.Feedback;
import com.growthdiary.sessionlog.tracker.feedback.FeedbackRepository;
import com.growthdiary.sessionlog.tracker.time.Time;
import com.growthdiary.sessionlog.tracker.time.TimeRepository;
import com.growthdiary.sessionlog.tracker.validators.SessionDTOValidator;
import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;


@Service
public class SessionService {

    private final SessionRepository sessionRepository;

    private final DetailsRepository detailsRepository;
    private final TimeRepository timeRepository;
    private final FeedbackRepository feedbackRepository;

    private final SessionDTOValidator sessionDTOValidator;

    /**
     * Injects a sessionRepository object into the Spring context
     */
    @Autowired
    public SessionService(SessionRepository sessionRepository, DetailsRepository detailsRepository,
                          TimeRepository timeRepository, FeedbackRepository feedbackRepository,
                          SessionDTOValidator sessionDTOValidator) {
        this.sessionRepository = sessionRepository;
        this.detailsRepository = detailsRepository;
        this.timeRepository = timeRepository;
        this.feedbackRepository = feedbackRepository;
        this.sessionDTOValidator = sessionDTOValidator;
    }

    /**
     * Creates a Session object using the SessionDTO and saves it into the database.
     * Validates the given SessionDTO object.
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

            detailsRepository.save(details);
            timeRepository.save(time);
            feedbackRepository.save(feedback);

            Session session = new Session(details, time, feedback);
            return sessionRepository.save(session);
        }
    }

    /*
     * Utility method to build an error message based on all the field errors returned by the Errors object
     * Provides end-user a more readable format to understand issues with the given input
     */
    private String createErrorMessage(Errors errors) {
        StringBuilder errorBuilder = new StringBuilder("Validation failed. Reported error(s): ");
        for (ObjectError objectError : errors.getAllErrors()) {
            errorBuilder.append(objectError.getDefaultMessage()).append("; ");
        }
        return errorBuilder.toString();
    }
}
