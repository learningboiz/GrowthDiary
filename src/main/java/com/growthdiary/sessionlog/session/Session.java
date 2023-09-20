package com.growthdiary.sessionlog.session;

import com.growthdiary.sessionlog.details.Details;
import com.growthdiary.sessionlog.feedback.Feedback;
import com.growthdiary.sessionlog.time.Time;
import jakarta.persistence.*;

/**
 * Main entity to represent a user learning session
 */
@Entity
public class Session {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToOne
    @JoinColumn(name = "details_id")
    private Details details;

    @OneToOne
    @JoinColumn(name = "time_id")
    private Time time;

    @ManyToOne
    @JoinColumn(name = "feedback_id")
    private Feedback feedback;

    public Session(Details details, Time time, Feedback feedback) {
        this.details = details;
        this.time = time;
        this.feedback = feedback;
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
