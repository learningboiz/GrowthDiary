package com.growthdiary.sessionlog.session;

import com.growthdiary.sessionlog.details.Details;
import com.growthdiary.sessionlog.details.DetailsService;
import com.growthdiary.sessionlog.feedback.Feedback;
import com.growthdiary.sessionlog.feedback.FeedbackService;
import com.growthdiary.sessionlog.time.Time;
import com.growthdiary.sessionlog.time.TimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class SessionService {

    private final SessionRepository sessionRepository;
    private final DetailsService detailsService;
    private final TimeService timeService;
    private final FeedbackService feedbackService;

    /**
     * Injects a sessionRepository object into the Spring context to be used for each creation of Session object
     * @param sessionRepository
     */
    @Autowired
    public SessionService(SessionRepository sessionRepository,
                          DetailsService detailsService,
                          TimeService timeService,
                          FeedbackService feedbackService) {
        this.detailsService = detailsService;
        this.timeService = timeService;
        this.feedbackService = feedbackService;
        this.sessionRepository = sessionRepository;
    }

    /**
     * Take in a sessionDTO input and creates a Session object
     * @param sessionDTO SessionDTO that wraps the details, time and feedback objects
     * @return Session object
     */
    public Session createSession(SessionDTO sessionDTO) {

        Details details = sessionDTO.getDetails();
        Time time = sessionDTO.getTime();
        Feedback feedback = sessionDTO.getFeedback();
        return new Session(details, time, feedback);
    }

    public void saveSessionDetails(Session session) {
        detailsService.saveDetails(session.getDetails());
        timeService.saveTime(session.getTime());
        feedbackService.saveFeedback(session.getFeedback());
        sessionRepository.save(session);
    }
}
