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
