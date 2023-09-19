package com.growthdiary.sessionlog.session;

import com.growthdiary.sessionlog.details.Details;
import com.growthdiary.sessionlog.feedback.Feedback;
import com.growthdiary.sessionlog.time.Time;

public class SessionDTO {

    private Details details;

    private Time time;

    private Feedback feedback;

    public SessionDTO() {
    }

    public void setDetails(Details details) {
        this.details = details;
    }

    public void setTime(Time time) {
        this.time = time;
    }

    public void setFeedback(Feedback feedback) {
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
