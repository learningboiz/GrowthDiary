package com.growthdiary.sessionlog.tracker.models;

import jakarta.persistence.*;

@Embeddable
public class Details {

    private String skill;

    private String description;

    /*
     * A no-args constructor is required by Hibernate when inserting data into the database
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
