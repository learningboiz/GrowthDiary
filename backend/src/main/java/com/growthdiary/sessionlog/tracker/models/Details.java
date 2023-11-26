package com.growthdiary.sessionlog.tracker.models;

import jakarta.persistence.*;

@Embeddable
public class Details {

    private String topic;

    private String description;

    /*
     * A no-args constructor is required by Hibernate when inserting data into the database
     */
    public Details(){}

    public Details(String topic, String description) {
        this.topic = topic;
        this.description = description;
    }

    public void setTopic(String skill) {
        this.topic = skill;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTopic() {
        return this.topic;
    }

    public String getDescription() {
        return this.description;
    }
}
