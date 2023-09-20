package com.growthdiary.sessionlog.session;

import com.growthdiary.sessionlog.details.Details;
import com.growthdiary.sessionlog.feedback.Feedback;
import com.growthdiary.sessionlog.time.Time;

public class SessionDTO {

    private Details details;

    private Time time;

    private Feedback feedback;

    public SessionDTO(Details details, Time time, Feedback feedback) {
        this.details = details;
        this.time = time;
        this.feedback = feedback;
    }

    public Details getDetails() { return this.details; }

    public Time getTime() {
        return this.time;
    }

    public Feedback getFeedback() {
        return this.feedback;
    }
}
