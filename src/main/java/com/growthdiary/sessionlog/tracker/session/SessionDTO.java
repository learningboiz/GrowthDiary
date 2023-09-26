package com.growthdiary.sessionlog.tracker.session;

import java.time.LocalDateTime;

public class SessionDTO {

    private String skill;
    private String description;
    private LocalDateTime startPeriod;
    private LocalDateTime endPeriod;
    private String distraction;
    private Integer productivity;

    public SessionDTO() {
    }

    public SessionDTO(String skill, String description, LocalDateTime startPeriod, LocalDateTime endPeriod,
                      Integer productivity, String distraction) {
        this.skill = skill;
        this.description = description;
        this.startPeriod = startPeriod;
        this.endPeriod = endPeriod;
        this.productivity = productivity;
        this.distraction = distraction;
    }

    public String getSkill() {
        return this.skill;
    }

    public String getDescription() {
        return this.description;
    }

    public LocalDateTime getStartPeriod() {
        return this.startPeriod;
    }

    public LocalDateTime getEndPeriod() {
        return this.endPeriod;
    }

    public Integer getProductivity() {
        return this.productivity;
    }
    public String getDistraction() {
        return this.distraction;
    }
}
