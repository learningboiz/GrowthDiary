package com.growthdiary.sessionlog.history.historyfilter;

import java.time.LocalDate;

public class TimeFilter {

    private FilterOperations dateOperation;
    private LocalDate primaryDate;
    private LocalDate secondaryDate;
    private FilterOperations durationOperation;
    private Long primaryDuration;
    private Long secondaryDuration;

    private TimeFilter() {}

    public FilterOperations getDateOperation() {
        return this.dateOperation;
    }

    public LocalDate getPrimaryDate() {
        return this.primaryDate;
    }

    public LocalDate getSecondaryDate() {
        return this.secondaryDate;
    }

    public FilterOperations getDurationOperation() {
        return this.durationOperation;
    }

    public Long getPrimaryDuration() {
        return this.primaryDuration;
    }

    public Long getSecondaryDuration() {
        return this.secondaryDuration;
    }

    public static class BuildFilter {
        private final TimeFilter filter = new TimeFilter();

        public BuildFilter filterDates (LocalDate primaryDate, FilterOperations operation) {
            filter.primaryDate = primaryDate;
            filter.dateOperation = operation;
            return this;
        }

        public BuildFilter filterDates (LocalDate primaryDate, LocalDate secondaryDate) {
            filter.primaryDate = primaryDate;
            filter.secondaryDate = secondaryDate;
            filter.dateOperation = FilterOperations.BETWEEN;
            return this;
        }

        public BuildFilter filterDuration (Long primaryDuration, FilterOperations operation) {
            filter.primaryDuration = primaryDuration;
            filter.durationOperation = operation;
            return this;
        }

        public BuildFilter filterDuration (Long primaryDuration, Long secondaryDuration) {
            filter.primaryDuration = primaryDuration;
            filter.secondaryDuration = secondaryDuration;
            filter.durationOperation = FilterOperations.BETWEEN;
            return this;
        }

        public TimeFilter build() {
            return filter;
        }
    }
}
