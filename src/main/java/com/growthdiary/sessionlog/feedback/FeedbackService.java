package com.growthdiary.sessionlog.feedback;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Main service that takes in user input to create feedback object
 */
@Service
public class FeedbackService {

    private final FeedbackRepository feedbackRepository;

    /**
     * Dependency injection of repo since only one instance is required to communicate with database
     * @param feedbackRepository bean instance of the feedbackRepository injected by Spring
     */
    @Autowired
    public FeedbackService(FeedbackRepository feedbackRepository) {
        this.feedbackRepository = feedbackRepository;
    }

    /**
     * Take in user input to create a Feedback object
     * @param productivity a rating of session productivity level on a scale of 1 to 5
     * @param distraction the most common distraction encountered during the session
     * @return a Feedback object
     */
    public Feedback createFeedback(Integer productivity,
                                   String distraction)
    {
        return new Feedback(productivity, distraction);
    }

    /**
     * Takes in a Feedback object and persist it into the database
     * @param feedback Feedback object
     */
    public void saveFeedback(Feedback feedback) {
        feedbackRepository.save(feedback);
    }
}
