package com.growthdiary.sessionlog.history.dtos;

import com.growthdiary.sessionlog.history.historysort.SortDirection;

public class SortRequestDTO {

    private final String property;

    private final SortDirection sortDirection;

    public SortRequestDTO(String property, SortDirection sortDirection) {
        this.property = property;
        this.sortDirection = sortDirection;
    }

    public String getProperty() {
        return this.property;
    }

    public SortDirection getSortDirection() {
        return this.sortDirection;
    }
}
