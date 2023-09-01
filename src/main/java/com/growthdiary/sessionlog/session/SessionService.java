package com.growthdiary.sessionlog.session;

import com.growthdiary.sessionlog.feedback.Feedback;
import com.growthdiary.sessionlog.feedback.FeedbackService;
import com.growthdiary.sessionlog.skill.Skill;
import com.growthdiary.sessionlog.skill.SkillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;

@Service
public class SessionService {

    private final SessionRepository sessionRepository;
    private final FeedbackService feedbackService;
    private final SkillService skillService;

    /** Inject the sessionRepository bean into the Spring context since only one instance is required
     *
     * @param sessionRepository bean instance to store session data and interact with database
     * @param feedbackService bean instance to inherit methods for creating Feedback object
     * @param skillService bean instance to inherit methods for creating Skill object
     */
    @Autowired
    public SessionService(SessionRepository sessionRepository,
                          FeedbackService feedbackService,
                          SkillService skillService) {
        this.sessionRepository = sessionRepository;
        this.feedbackService = feedbackService;
        this.skillService = skillService;

    }

    /** Creates a Session object based on required parameters and saves it to the database
     *
     * @param startPeriod the date and time during which the session begins
     * @param endPeriod the date and time during which the session ends
     * @param topic the specific focus on the session
     * @param category the overarching category that the topic is part of
     * @param rating a rating of session productivity level on a scale of 1 to 5
     * @param distraction the most common distraction encountered during the session
     * @param emotion an emotion to best describe how the session went
     * @return Session object including dates and created Skill and Feedback objects
     */
    public Session createSession(LocalDateTime startPeriod,
                                 LocalDateTime endPeriod,
                                 String topic,
                                 String category,
                                 Integer rating,
                                 String distraction,
                                 String emotion) {
        Skill skill = skillService.createSkill(topic, category);
        Feedback feedback = feedbackService.createFeedback(rating, distraction, emotion);

        Session session = new Session();
        session.setStartPeriod(startPeriod);
        session.setEndPeriod(endPeriod);
        session.setSessionDuration(startPeriod, endPeriod);
        session.setSkill(skill);
        session.setFeedback(feedback);

        sessionRepository.save(session);

        return session;
    }
}
