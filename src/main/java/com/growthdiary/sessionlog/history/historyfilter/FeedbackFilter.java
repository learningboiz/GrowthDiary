package com.growthdiary.sessionlog.history.historyfilter;

import java.util.List;

public class FeedbackFilter {

    private List<String> distractions;

    private FilterOperations productivityOperation;
    private Integer primaryProductivity;

    private Integer secondaryProductivity;

    private FeedbackFilter() {
    }

    public List<String> getDistractions() {
        return this.distractions;
    }

    public FilterOperations getProductivityOperation() {
        return this.productivityOperation;
    }

    public Integer getPrimaryProductivity() {
        return this.primaryProductivity;
    }

    public Integer getSecondaryProductivity() {
        return this.secondaryProductivity;
    }

    public static class BuildFilter {
        private final FeedbackFilter filter = new FeedbackFilter();

        public BuildFilter filterDistractions(List<String> distractions) {
            filter.distractions = distractions;
            return this;
        }

        public BuildFilter filterProductivity (Integer primaryProductivity, FilterOperations operation) {
            filter.primaryProductivity = primaryProductivity;
            filter.productivityOperation = operation;
            return this;
        }

        public BuildFilter filterProductivity (Integer primaryProductivity, Integer secondaryProductivity) {
            filter.primaryProductivity = primaryProductivity;
            filter.secondaryProductivity = secondaryProductivity;
            filter.productivityOperation = FilterOperations.BETWEEN;
            return this;
        }

        public FeedbackFilter build() {
            return filter;
        }
    }
}
