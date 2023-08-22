package com.growthdiary.sessionlog.Feedback;

public class Feedback {

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
