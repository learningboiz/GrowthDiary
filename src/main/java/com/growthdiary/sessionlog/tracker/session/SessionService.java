package com.growthdiary.sessionlog.tracker.session;

import com.growthdiary.sessionlog.tracker.details.Details;
import com.growthdiary.sessionlog.tracker.details.DetailsService;
import com.growthdiary.sessionlog.tracker.feedback.Feedback;
import com.growthdiary.sessionlog.tracker.feedback.FeedbackService;
import com.growthdiary.sessionlog.tracker.time.Time;
import com.growthdiary.sessionlog.tracker.time.TimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class SessionService {

    private final SessionRepository sessionRepository;
    private final DetailsService detailsService;
    private final TimeService timeService;
    private final FeedbackService feedbackService;

    /**
     * Injects a sessionRepository object into the Spring context
     * Injects services for Details, Time and Feedback models for instantiation of objects during session creation
     * @param sessionRepository
     * @param detailsService
     * @param timeService
     * @param feedbackService
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
     * Creates a Session object using the SessionDTO and saves it into the database
     * Creates Details, Time and Feedback objects using respective model services
     * @param sessionDTO SessionDTO that wraps the details, time and feedback objects
     * @return Session object
     */
    public Session createSession(SessionDTO sessionDTO) {

        Details details = detailsService.createDetails(sessionDTO.getSkill(), sessionDTO.getDescription());
        Time time = timeService.createTime(sessionDTO.getStartPeriod(), sessionDTO.getEndPeriod());
        Feedback feedback = feedbackService.createFeedback(sessionDTO.getProductivity(), sessionDTO.getDistraction());

        Session session = new Session(details, time, feedback);
        return sessionRepository.save(session);
    }
}
