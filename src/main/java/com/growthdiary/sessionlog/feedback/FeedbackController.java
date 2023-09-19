package com.growthdiary.sessionlog.feedback;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/session")
public class FeedbackController {

    private final FeedbackService feedbackService;

    @Autowired
    public FeedbackController(FeedbackService feedbackService) {
        this.feedbackService = feedbackService;
    }

    /**
     * Handles the creation of Feedback object for a user's session
     * @return Feedback object
     */
    @PostMapping("/feedback")
    public ResponseEntity<Feedback> feedback(@RequestParam Integer productivity,
                                             @RequestParam String distraction) {
        Feedback feedback = feedbackService.createFeedback(productivity, distraction);
        return new ResponseEntity<>(feedback, HttpStatus.CREATED);
    }
}
