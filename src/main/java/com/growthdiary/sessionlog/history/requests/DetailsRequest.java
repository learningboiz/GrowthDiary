package com.growthdiary.sessionlog.history.requests;

import java.util.List;

public class DetailsRequest {
    private List<String> skills;

    private String description;

    public DetailsRequest() {

    }

    public void setSkills(List<String> skills) {
        this.skills = skills;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<String> getSkills() {
        return this.skills;
    }

    public String getDescription() {
        return this.description;
    }
 }
