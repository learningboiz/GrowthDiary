package com.growthdiary.sessionlog.history.filters;

import java.time.LocalDate;
import java.util.List;

public class FilterRequest {

    private String entity;

    private FilterOperations operator;

    private String property;

    private List<String> skills;

    private List<String> description;

    private List<Long> durations;

    private List<LocalDate> dates;

    private List<Integer> productivity;

    private List<String> distractions;

    private FilterRequest() {}

    /*
    Builder method for the FilterRequest object
    Builder pattern used to support the creation of objects with varying list types
     */
    public static class Builder {
        private final FilterRequest request = new FilterRequest();

        public Builder filterBySkill(List<String> skills) {
            request.entity = "details";
            request.property = "skill";
            request.skills = skills;
            return this;
        }

        public Builder filterByDescription(List<String> description) {
            request.entity = "details";
            request.property = "description";
            request.description = description;
            return this;
        }

        public Builder filterByDuration(List<Long> durations, FilterOperations operation) {
            request.entity = "time";
            request.property = "duration";
            request.operator = operation;
            request.durations = durations;
            return this;
        }

        public Builder filterByDate(List<LocalDate> dates, FilterOperations operation) {
            request.entity = "time";
            request.property = "startDate";
            request.operator = operation;
            request.dates = dates;
            return this;
        }

        public Builder filterByProductivity(List<Integer> productivity, FilterOperations operation) {
            request.entity = "feedback";
            request.property = "productivity";
            request.operator = operation;
            request.productivity = productivity;
            return this;
        }

        public Builder filterByDistraction(List<String> distraction) {
            request.entity = "feedback";
            request.property = "distraction";
            request.distractions = distraction;
            return this;
        }

        public FilterRequest build() {
            return request;
        }
    }


    public String getEntity(){
        return this.entity;
    }

    public FilterOperations getOperator() {
        return this.operator;
    }

    public String getProperty() {
        return this.property;
    }
    public List<String> getSkills() {
        return this.skills;
    }

    public List<String> getDescriptions() {
        return this.description;
    }

    public List<Long> getDurations() {
        return this.durations;
    }

    public List<LocalDate> getDates() {
        return this.dates;
    }

    public List<Integer> getProductivity() {
        return this.productivity;
    }

    public List<String> getDistractions() {
        return this.distractions;
    }
}
