package com.growthdiary.sessionlog.history;

import java.util.List;

public class FilterDTO {

    private String entity;

    private FilterOperators operator;

    private String property;

    private List<String> values;

    public FilterDTO() {}

    public FilterDTO(String entity, FilterOperators operator, String property, List<String> values) {
        this.entity = entity;
        this.operator = operator;
        this.property = property;
        this.values = values;
    }

    public String getEntity() {
        return this.entity;
    }

    public FilterOperators getOperator() {
        return this.operator;
    }

    public String getProperty() {
        return this.property;
    }

    public List<String> values() {
        return this.values;
    }





}
