package com.growthdiary.sessionlog.session;

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

    private String skill;

    private String description;

    @OneToOne
    @JoinColumn(name = "time_id")
    private Time time;

    @ManyToOne
    @JoinColumn(name = "feedback_id")
    private Feedback feedback;

    public Session() {
    }

    public void setSkill(String skill) {
        this.skill = skill;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setSessionTime(Time time) {
        this.time = time;
    }

    public void setFeedback(Feedback feedback) {
        this.feedback = feedback;
    }

    public String getSkill() {
        return this.skill;
    }

    public String getDescription() {
        return this.description;
    }

    public Time getTime() {
        return this.time;
    }

    public Feedback getFeedback() {
        return this.feedback;
    }
}
