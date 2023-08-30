package com.growthdiary.sessionlog.skill;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

@Entity
public class Skill {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @NotBlank
    private String topic;
    @NotBlank(message = "Category must be filled in")
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
