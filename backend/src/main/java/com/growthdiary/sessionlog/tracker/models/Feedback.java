package com.growthdiary.sessionlog.tracker.models;

import jakarta.persistence.*;

@Embeddable
public class Feedback {

    private Integer productivity;

    private String distraction;

    /*
     * A no-args constructor is required by Hibernate when inserting data into the database
     */
    public Feedback() {}

    public Feedback(Integer productivity, String distraction) {
        this.productivity = productivity;
        this.distraction = distraction;
    }

    public void setProductivity(Integer productivityRating) {
        this.productivity = productivityRating;
    }

    public Integer getProductivity() {
        return this.productivity;
    }

    public void setDistraction(String keyDistraction) {
        this.distraction = keyDistraction;
    }

    public String getDistraction() {
        return this.distraction;
    }
}
