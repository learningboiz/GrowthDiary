package com.growthdiary.sessionlog.session;

import com.growthdiary.sessionlog.feedback.Feedback;
import com.growthdiary.sessionlog.skill.Skill;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;


@Entity
public class Session {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private LocalDateTime startPeriod;

    private LocalDateTime endPeriod;

    private Long duration;

    @ManyToOne
    @JoinColumn(name = "skill_id")
    private Skill skill;

    @OneToOne
    @JoinColumn(name = "feedback_id")
    private Feedback feedback;

    public Session() {
    }

    // Setters
    public void setStartPeriod(LocalDateTime startPeriod) {this.startPeriod = startPeriod;}

    public void setEndPeriod(LocalDateTime endPeriod) {this.endPeriod = endPeriod;}

    public void setSkill(Skill skill) {
        this.skill = skill;
    }

    public void setFeedback(Feedback feedback) {
        this.feedback = feedback;
    }

    /** Sets session duration in minutes */
    public void setSessionDuration(LocalDateTime start, LocalDateTime end) {
        this.duration = ChronoUnit.MINUTES.between(start, end);
    }

    public Long getSessionDuration() {
        return this.duration;
    }

    // Getters
    public LocalDateTime getStartPeriod() {return startPeriod;}

    public LocalDateTime getEndPeriod() {return endPeriod;}

    public Skill getSkill() {
        return skill;
    }

    public Feedback getFeedback() {
        return feedback;
    }

}
