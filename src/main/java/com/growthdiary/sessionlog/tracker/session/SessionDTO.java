package com.growthdiary.sessionlog.tracker.session;

import com.growthdiary.sessionlog.tracker.details.Details;
import com.growthdiary.sessionlog.tracker.feedback.Feedback;
import com.growthdiary.sessionlog.tracker.time.Time;

public class SessionDTO {

    private final Details details;

    private final Time time;

    private final Feedback feedback;

    public SessionDTO(Details details, Time time, Feedback feedback) {
        this.details = details;
        this.time = time;
        this.feedback = feedback;
    }

    public Details getDetails() {
        return this.details;
    }

    public Time getTime() {
        return this.time;
    }

    public Feedback getFeedback() {
        return this.feedback;
    }
}
