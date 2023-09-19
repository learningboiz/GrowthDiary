package com.growthdiary.sessionlog.session;

import com.growthdiary.sessionlog.details.Details;
import com.growthdiary.sessionlog.feedback.Feedback;
import com.growthdiary.sessionlog.time.Time;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class SessionService {

    private final SessionRepository sessionRepository;

    /**
     * Injects a sessionRepository object into the Spring context to be used for each creation of Session object
     * @param sessionRepository
     */
    @Autowired
    public SessionService(SessionRepository sessionRepository) {
        this.sessionRepository = sessionRepository;
    }

    /**
     * Take in a sessionDTO input and creates a Session object
     * @param sessionDTO SessionDTO that wraps the details, time and feedback objects
     * @return Session object
     */
    public Session createSession(SessionDTO sessionDTO) {

        Session session = new Session();
        session.setDetails(sessionDTO.getDetails());
        session.setTime(sessionDTO.getTime());
        session.setFeedback(sessionDTO.getFeedback());
        return session;
    }

    public void saveSession(Session session) {
        sessionRepository.save(session);
    }
}
