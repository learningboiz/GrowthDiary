package com.growthdiary.sessionlog.feedback;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FeedbackService {

    private final FeedbackRepository feedbackRepository;

    @Autowired
    public FeedbackService(FeedbackRepository feedbackRepository) {
        this.feedbackRepository = feedbackRepository;
    }

    /** Creates a Feedback object instance to be injected into the Session object
     *
     * @param rating a productivity rating on a given scale (tentatively 1 - 5)
     * @param distraction a key distraction that occurred during the session
     * @param emotion an emotion that describes how user felt about the session
     * @return a feedback object
     */
    public Feedback createFeedback(Integer rating,
                                   String distraction,
                                   String emotion)
    {
        Feedback feedback = new Feedback();
        feedback.setProductivity(rating);
        feedback.setDistractions(distraction);
        feedback.setEmotions(emotion);
        feedbackRepository.save(feedback);

        return feedback;
    }
}
