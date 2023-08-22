package com.growthdiary.sessionlog.session;

import com.growthdiary.sessionlog.feedback.Feedback;
import com.growthdiary.sessionlog.skill.Skill;
import com.growthdiary.sessionlog.studymaterials.StudyMaterials;

import java.time.*;

public class Session {

    private LocalDate date;
    private LocalTime startTime;
    private LocalTime endTime;

    private Skill skill;
    private StudyMaterials studyMaterials;
    private Feedback feedback;

    public Session() {
    }

    // Setters
    public void setDate(LocalDate date) {
        this.date = date;
    }

    public void setStartTime(LocalTime startTime) {
        this.startTime = startTime;
    }

    public void setEndTime(LocalTime endTime) {
        this.endTime = endTime;
    }

    public void setSkill(Skill skill) {
        this.skill = skill;
    }

    public void setStudyMaterials(StudyMaterials studyMaterials) {
        this.studyMaterials = studyMaterials;
    }

    public void setFeedback(Feedback feedback) {
        this.feedback = feedback;
    }

    // Getters
    public LocalDate getDate() {
        return date;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public LocalTime getEndTime() {
        return endTime;
    }

    public Skill getSkill() {
        return skill;
    }

    public StudyMaterials getStudyMaterials() {
        return studyMaterials;
    }

    public Feedback getFeedback() {
        return feedback;
    }
}
