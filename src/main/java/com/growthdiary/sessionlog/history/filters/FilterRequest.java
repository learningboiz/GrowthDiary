package com.growthdiary.sessionlog.history.filters;

import java.time.LocalDate;
import java.util.List;

public class FilterRequest {

    private String entity;

    private FilterOperators operator;

    private String property;

    private List<String> skills;

    private List<String> descriptions;

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

        public Builder entity(String entity) {
            request.entity = entity;
            return this;
        }

        public Builder operator(FilterOperators operator) {
            request.operator = operator;
            return this;
        }

        public Builder property(String property) {
            request.property = property;
            return this;
        }

        public Builder skills(List<String> skills) {
            request.skills = skills;
            return this;
        }

        public Builder descriptions(List<String> descriptions) {
            request.descriptions = descriptions;
            return this;
        }

        public Builder durations(List<Long> durations) {
            request.durations = durations;
            return this;
        }

        public Builder dates(List<LocalDate> dates) {
            request.dates = dates;
            return this;
        }

        public Builder productivity(List<Integer> productivity) {
            request.productivity = productivity;
            return this;
        }

        public Builder distractions(List<String> distractions) {
            request.distractions = distractions;
            return this;
        }

        public FilterRequest build() {
            return request;
        }
    }


    public String getEntity(){
        return this.entity;
    }

    public FilterOperators getOperator() {
        return this.operator;
    }

    public String getProperty() {
        return this.property;
    }
    public List<String> getSkills() {
        return this.skills;
    }

    public List<String> getDescriptions() {
        return this.descriptions;
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
