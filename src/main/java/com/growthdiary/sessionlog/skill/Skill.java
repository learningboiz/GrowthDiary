package com.growthdiary.sessionlog.skill;

public class Skill {
    private Long id;
    private String topic;
    private String category;

    public Skill() {
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getTopic() {
        return this.topic;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getCategory() {
        return this.category;
    }
}
