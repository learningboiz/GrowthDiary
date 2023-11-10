package com.growthdiary.sessionlog.history.historyfilter;

import java.util.List;

/**
 * A filter used to create a customised view of past sessions that match the specified feedback-related criteria
 *
 * @see DetailsFilter
 * @see TimeFilter
 */
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

    /**
     * Builder class used to create a FeedbackFilter object.
     * Allows creation of filter that finds sessions which match the given distractions, productivity or both.
     */
    public static class BuildFilter {
        private final FeedbackFilter filter = new FeedbackFilter();

        public BuildFilter findDistractionIn(List<String> distractions) {
            filter.distractions = distractions;
            return this;
        }

        public BuildFilter findProductivity(FilterOperations operation, Integer primaryProductivity) {
            filter.primaryProductivity = primaryProductivity;
            filter.productivityOperation = operation;
            return this;
        }

        public BuildFilter findProductivityBetween(Integer primaryProductivity, Integer secondaryProductivity) {
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
