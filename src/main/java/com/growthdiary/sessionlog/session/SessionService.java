package com.growthdiary.sessionlog.session;

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
     * Takes user input to create a Session object to be saved into the database
     * @param skill The name of the skill user is working on
     * @param description Specifics of how user is learning that skill
     * @param time Start, end and duration of the session
     * @param feedback Feedback for the learning session
     * @return Session object
     */
    public Session createSession(String skill,
                                 String description,
                                 Time time,
                                 Feedback feedback) {

        Session session = new Session();
        session.setSkill(skill);
        session.setDescription(description);
        session.setSessionTime(time);
        session.setFeedback(feedback);

        sessionRepository.save(session);

        return session;
    }
}
