package com.growthdiary.sessionlog.tracker.models;

import jakarta.persistence.*;

@Embeddable
public class Feedback {

    private Integer productivity;

    private String obstacle;

    /*
     * A no-args constructor is required by Hibernate when inserting data into the database
     */
    public Feedback() {}

    public Feedback(Integer productivity, String obstacle) {
        this.productivity = productivity;
        this.obstacle = obstacle;
    }

    public void setProductivity(Integer productivityRating) {
        this.productivity = productivityRating;
    }

    public Integer getProductivity() {
        return this.productivity;
    }

    public void setObstacle(String keyDistraction) {
        this.obstacle = keyDistraction;
    }

    public String getObstacle() {
        return this.obstacle;
    }
}
