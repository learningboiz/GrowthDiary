package com.growthdiary.sessionlog.feedback;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FeedbackController {

    private FeedbackService feedbackService;

    @Autowired
    public FeedbackController(FeedbackService feedbackService) {
        this.feedbackService = feedbackService;
    }

    @PostMapping("/feedback")
    public ResponseEntity<Feedback> feedback(@RequestParam Integer rating,
                                             @RequestParam String distraction,
                                             @RequestParam String emotion) {

        Feedback feedback = feedbackService.createFeedback(rating, distraction, emotion);
        return new ResponseEntity<>(feedback, HttpStatus.CREATED);
    }
}
