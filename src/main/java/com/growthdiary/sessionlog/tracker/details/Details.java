package com.growthdiary.sessionlog.tracker.details;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Details {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String skill;

    private String description;

    /* Default no-argument constructor required by Hibernate
     * Used during database queries
     */
    public Details(){}

    public Details(String skill, String description) {
        this.skill = skill;
        this.description = description;
    }

    public void setSkill(String skill) {
        this.skill = skill;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSkill() {
        return this.skill;
    }

    public String getDescription() {
        return this.description;
    }
}
