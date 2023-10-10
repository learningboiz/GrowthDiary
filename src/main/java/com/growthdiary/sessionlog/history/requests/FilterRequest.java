package com.growthdiary.sessionlog.history.requests;

import com.growthdiary.sessionlog.history.filters.FilterOperations;

import java.time.LocalDate;
import java.util.List;

/**
 * The {@code FilterRequest} class represents an object containing customised requests for viewing session history.
 * It uses the builder pattern to create customised filter requests
 * <p>
 * Use the {@link com.growthdiary.sessionlog.history.requests.FilterRequest.BuildRequest} class to configure a filter request
 */
public class FilterRequest {

    private DetailsRequest detailsRequest;

    private TimeRequest timeRequest;

    private FeedbackRequest feedbackRequest;

    private FilterRequest() {
    }

    public DetailsRequest getDetailsRequest() {
        return this.detailsRequest;
    }

    public TimeRequest getTimeRequest() {
        return this.timeRequest;
    }

    public FeedbackRequest getFeedbackRequest() {
        return this.feedbackRequest;
    }

    /**
     * The {@code BuildRequest} class provides a builder interface for creating a {@code FilterRequest}.
     * Use this builder to set various filtering criteria.
     */
    public static class BuildRequest {

        private final FilterRequest request = new FilterRequest();
        private DetailsRequest detailsRequest;

        private TimeRequest timeRequest;

        private FeedbackRequest feedbackRequest;
        private boolean filtersApplied = false;

        private void markFilterApplied() {
            this.filtersApplied = true;
        }

        public BuildRequest filterSkill(List<String> skills) {
            if (detailsRequest == null) {
                detailsRequest = new DetailsRequest();
            }
            detailsRequest.setSkills(skills);
            markFilterApplied();
            return this;
        }

        public BuildRequest filterDescription(String description) {
            if (detailsRequest == null) {
                detailsRequest = new DetailsRequest();
            }
            detailsRequest.setDescription(description);
            markFilterApplied();
            return this;
        }

        public BuildRequest filterDate(LocalDate minDate, LocalDate maxDate, FilterOperations dateOp) {
            RequestValidator.validateDate(minDate, maxDate, dateOp);

            if (timeRequest == null) {
                timeRequest = new TimeRequest();
            }
            timeRequest.setMinDate(minDate);
            timeRequest.setMaxDate(maxDate);
            timeRequest.setDateOp(dateOp);
            markFilterApplied();
            return this;
        }

        public BuildRequest filterDuration(Long minDuration, Long maxDuration, FilterOperations durationOp) {
            RequestValidator.validateDuration(minDuration, maxDuration, durationOp);

            if (timeRequest == null) {
                timeRequest = new TimeRequest();
            }

            timeRequest.setMinDuration(minDuration);
            timeRequest.setMaxDuration(maxDuration);
            timeRequest.setDurationOp(durationOp);
            markFilterApplied();
            return this;
        }

        public BuildRequest filterProductivity(Integer minProd, Integer maxProd, FilterOperations prodOp) {
            RequestValidator.validateProductivity(minProd, maxProd, prodOp);

            if (feedbackRequest == null) {
                feedbackRequest = new FeedbackRequest();
            }

            feedbackRequest.setMinProd(minProd);
            feedbackRequest.setMaxProd(maxProd);
            feedbackRequest.setProdOp(prodOp);
            markFilterApplied();
            return this;
        }

        public BuildRequest filterDistractions(List<String> distractions) {
            if (feedbackRequest == null) {
                feedbackRequest = new FeedbackRequest();
            }
            feedbackRequest.setDistractions(distractions);
            markFilterApplied();
            return this;
        }

        public FilterRequest build() {
            if (!filtersApplied) {
                throw new UnsupportedOperationException("At least one filter must be applied");
            } else {
                request.detailsRequest = detailsRequest;
                request.timeRequest = timeRequest;
                request.feedbackRequest = feedbackRequest;
                return request;
            }
        }
    }
}
