package com.growthdiary.sessionlog.feedback;

import jakarta.persistence.*;

@Entity
public class Feedback {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    private Integer productivity;

    private String distractions;

    private String emotions;

    public Feedback() {
    }

    public void setProductivity(Integer rating) {
        this.productivity = rating;
    }

    public Integer getProductivity() {
        return this.productivity;
    }

    public void setDistractions(String factors) {
        this.distractions = factors;
    }

    public String getDistractions() {
        return this.distractions;
    }

    public void setEmotions(String emotion) {
        this.emotions = emotion;
    }

    public String getEmotions() {
        return this.emotions;
    }
}
