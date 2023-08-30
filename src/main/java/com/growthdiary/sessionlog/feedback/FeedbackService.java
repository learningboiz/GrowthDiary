package com.growthdiary.sessionlog.feedback;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FeedbackService {

    private final FeedbackRepository feedbackRepository;

    /** Inject the feedbackRepository bean into the Spring context since only one instance is required
     *
     * The bean instance will be solely responsible for interactions with the database
     * @param  feedbackRepository bean instance of the feedbackRepository injected by Spring
     */
    @Autowired
    public FeedbackService(FeedbackRepository feedbackRepository) {
        this.feedbackRepository = feedbackRepository;
    }

    /** Creates a Feedback object and saves the data into the database
     *
     * @param rating a rating of session productivity level on a scale of 1 to 5
     * @param distraction the most common distraction encountered during the session
     * @param emotion an emotion to best describe how the session went
     * @return a Feedback object to be included when creating a Session Object
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
