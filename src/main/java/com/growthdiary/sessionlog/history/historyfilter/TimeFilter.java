package com.growthdiary.sessionlog.history.requests;

import com.growthdiary.sessionlog.history.specifications.FilterOperations;

import java.time.LocalDate;

public class TimeRequest {

    private FilterOperations dateOp;

    private LocalDate minDate;
    private LocalDate maxDate;
    private FilterOperations durationOp;
    private Long minDuration;
    private Long maxDuration;

    public TimeRequest() {}

    public void setDateOp(FilterOperations dateOp) {
        this.dateOp = dateOp;
    }

    public FilterOperations getDateOp() {
        return this.dateOp;
    }

    public void setMinDate(LocalDate minDate) {
        this.minDate = minDate;
    }

    public LocalDate getMinDate() {
        return this.minDate;
    }

    public void setMaxDate(LocalDate maxDate) {
        this.maxDate = maxDate;
    }

    public LocalDate getMaxDate() {
        return this.maxDate;
    }

    public void setDurationOp(FilterOperations durationOp) {
        this.durationOp = durationOp;
    }

    public FilterOperations getDurationOp() {
        return this.durationOp;
    }

    public void setMinDuration(Long minDuration) {
        this.minDuration = minDuration;
    }

    public Long getMinDuration() {
        return this.minDuration;
    }

    public void setMaxDuration(Long maxDuration) {
        this.maxDuration = maxDuration;
    }

    public Long getMaxDuration() {
        return this.maxDuration;
    }
}
