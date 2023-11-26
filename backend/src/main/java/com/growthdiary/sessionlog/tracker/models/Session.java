package com.growthdiary.sessionlog.tracker.models;

import com.growthdiary.sessionlog.tracker.models.Details;
import com.growthdiary.sessionlog.tracker.models.Feedback;
import com.growthdiary.sessionlog.tracker.models.Time;
import jakarta.persistence.*;

/**
 * The {@code Session} entity represents a record of the user's learning session.
 * Currently, the session has three key attributes Details, Time and Feedback which are all required for a record to be formed.
 *
 * @see Details
 * @see Time
 * @see Feedback
 */
@Entity
public class Session {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    /*
     * Session attributes were encapsulated into separate classes to:
     * 1. Reduce number of parameters required for the Session constructor
     * 2. Improve code clarity and testability
     * 2. Decouple the Session object in case attributes are added/removed
     *
     * Utilised @Embedded so that all nested attributes are placed in a single Session table
     * instead of being split into multiple tables with 1:1 relationships. This helps to
     * reduce need for joins and streamlines database calls for other use cases
     */
    @Embedded
    private Details details;

    @Embedded
    private Time time;

    @Embedded
    private Feedback feedback;

    /*
     * A no-args constructor is required by Hibernate when inserting data into the database
     */
    public Session() {
    }

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
