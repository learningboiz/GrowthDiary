package com.growthdiary.sessionlog.history.filters;

import com.growthdiary.sessionlog.history.requests.DetailsRequest;
import com.growthdiary.sessionlog.history.requests.FeedbackRequest;
import com.growthdiary.sessionlog.history.requests.FilterRequest;
import com.growthdiary.sessionlog.history.requests.TimeRequest;
import com.growthdiary.sessionlog.tracker.session.Session;
import org.springframework.data.jpa.domain.Specification;

public class FilterMapper {

    public static Specification<Session> getFilteredSessions(FilterRequest filterRequest) {

        Specification<Session> allSpecifications = Specification.where(null);

        if (filterRequest.getDetailsRequest() != null) {
            allSpecifications = allSpecifications.and(filterByDetails(filterRequest.getDetailsRequest()));
        }

        if (filterRequest.getTimeRequest() != null) {
            allSpecifications = allSpecifications.and(filterByTime(filterRequest.getTimeRequest()));
        }

        if (filterRequest.getFeedbackRequest() != null) {
            allSpecifications = allSpecifications.and(filterByFeedback(filterRequest.getFeedbackRequest()));
        }
        return allSpecifications;
    }

    private static Specification<Session> filterByDetails(DetailsRequest detailsRequest) {

        Specification<Session> detailsSpecs = Specification.where(null);

        if (detailsRequest.getSkills() != null) {
            detailsSpecs = detailsSpecs.and(
                    FilterSpecifications.findValueIn(
                    detailsRequest.getSkills(),
                    "details",
                    "skill"
                    )
            );
        }

        if (detailsRequest.getDescription() != null) {
            detailsSpecs = detailsSpecs.and(
                    FilterSpecifications.findValuesLike(
                            detailsRequest.getDescription(),
                            "details",
                            "skill"
                    )
            );
        }

        return detailsSpecs;
    }

    private static Specification<Session> filterByTime(TimeRequest timeRequest) {

        Specification<Session> timeSpecs = Specification.where(null);

        if (timeRequest.getDateOp() != null) {
            timeSpecs = timeSpecs.and(
                    FilterSpecifications.compareValues(
                            timeRequest.getDateOp(),
                            timeRequest.getMinDate(),
                            timeRequest.getMaxDate(),
                            "time",
                            "startDate"
                    )
            );
        }

        if (timeRequest.getDurationOp() != null) {
            timeSpecs = timeSpecs.and(
                    FilterSpecifications.compareValues(
                            timeRequest.getDurationOp(),
                            timeRequest.getMinDuration(),
                            timeRequest.getMaxDuration(),
                            "time",
                            "duration"
                    )
            );
        }

        return timeSpecs;
    }

    private static Specification<Session> filterByFeedback(FeedbackRequest feedbackRequest) {

        Specification<Session> feedbackSpecs = Specification.where(null);

        if (feedbackRequest.getProdOp() != null) {
            feedbackSpecs = feedbackSpecs.and(
                    FilterSpecifications.compareValues(
                            feedbackRequest.getProdOp(),
                            feedbackRequest.getMinProd(),
                            feedbackRequest.getMaxProd(),
                            "feedback",
                            "productivity"
                    )
            );
        }

        if (feedbackRequest.getDistractions() != null) {
            feedbackSpecs = feedbackSpecs.and(
                    FilterSpecifications.findValueIn(
                            feedbackRequest.getDistractions(),
                            "feedback",
                            "distractions"
                    )
            );
        }

        return feedbackSpecs;
    }
}
