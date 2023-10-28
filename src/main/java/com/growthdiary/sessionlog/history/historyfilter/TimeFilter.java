package com.growthdiary.sessionlog.history.historyfilter;

import java.time.LocalDate;

/**
 * A filter used to create a customised view of past sessions that match the specified time-related criteria
 *
 * @see DetailsFilter
 * @see FeedbackFilter
 */
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

    /**
     * Builder class used to create a TimeFilter object.
     * Allows creation of filter that finds sessions which match the given dates, durations or both.
     */
    public static class BuildFilter {
        private final TimeFilter filter = new TimeFilter();

        public BuildFilter findDates(FilterOperations operation, LocalDate primaryDate) {
            filter.primaryDate = primaryDate;
            filter.dateOperation = operation;
            return this;
        }

        public BuildFilter findDatesBetween(LocalDate primaryDate, LocalDate secondaryDate) {
            filter.primaryDate = primaryDate;
            filter.secondaryDate = secondaryDate;
            filter.dateOperation = FilterOperations.BETWEEN;
            return this;
        }

        public BuildFilter findDuration(FilterOperations operation, Long primaryDuration) {
            filter.primaryDuration = primaryDuration;
            filter.durationOperation = operation;
            return this;
        }

        public BuildFilter findDurationsBetween(Long primaryDuration, Long secondaryDuration) {
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
