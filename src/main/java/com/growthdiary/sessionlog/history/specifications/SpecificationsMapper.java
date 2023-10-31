package com.growthdiary.sessionlog.history.specifications;

import com.growthdiary.sessionlog.history.historyfilter.DetailsFilter;
import com.growthdiary.sessionlog.history.historyfilter.FeedbackFilter;
import com.growthdiary.sessionlog.history.requests.FilterRequest;
import com.growthdiary.sessionlog.history.historyfilter.TimeFilter;
import com.growthdiary.sessionlog.tracker.models.Session;
import org.springframework.data.jpa.domain.Specification;

/**
 * The {@code SpecificationsMapper} class provides methods that maps attributes of a FilterRequestDTO
 * to their respective Specifications Builder.
 *
 * @see SpecificationsBuilder
 * @see FilterRequest
 */
public class SpecificationsMapper {

    public static Specification<Session> getAllSpecifications(FilterRequest filterRequest) {

        Specification<Session> allSpecifications = Specification.where(null);

        if (filterRequest.getDetailsFilter() != null) {
            allSpecifications = allSpecifications.and(buildDetailsSpecifications(filterRequest.getDetailsFilter()));
        }

        if (filterRequest.getTimeFilter() != null) {
            allSpecifications = allSpecifications.and(buildTimeSpecifications(filterRequest.getTimeFilter()));
        }

        if (filterRequest.getFeedbackFilter() != null) {
            allSpecifications = allSpecifications.and(buildFeedbackSpecifications(filterRequest.getFeedbackFilter()));
        }
        return allSpecifications;
    }

    private static Specification<Session> buildDetailsSpecifications(DetailsFilter detailsFilter) {

        String attribute = "details";
        Specification<Session> detailsSpecifications = Specification.where(null);

        if (detailsFilter.getSkills() != null) {
            String subAttribute = "skill";
            detailsSpecifications = detailsSpecifications.and(
                    SpecificationsBuilder.findValueIn(detailsFilter.getSkills(), attribute, subAttribute));
        }

        if (detailsFilter.getDescription() != null) {
            String subAttribute = "description";
            detailsSpecifications = detailsSpecifications.and(
                    SpecificationsBuilder.findValuesLike(detailsFilter.getDescription(), attribute, subAttribute));
        }

        return detailsSpecifications;
    }

    /*
     * Utility method that maps attributes of a TimeFilter to their respective Specification handler
     * compareValues() method handles these attributes since the filtering supports before, after, equals or during operations
     */
    private static Specification<Session> buildTimeSpecifications(TimeFilter timeFilter) {

        String attribute = "time";
        Specification<Session> timeSpecifications = Specification.where(null);

        if (timeFilter.getDateOperation() != null) {
            String subAttribute = "startDate";
            timeSpecifications = timeSpecifications.and(
                    SpecificationsBuilder.compareValues(
                            timeFilter.getDateOperation(),
                            timeFilter.getPrimaryDate(),
                            timeFilter.getSecondaryDate(),
                            attribute,
                            subAttribute
                    )
            );
        }

        if (timeFilter.getDurationOperation() != null) {
            String subAttribute = "duration";
            timeSpecifications = timeSpecifications.and(
                    SpecificationsBuilder.compareValues(
                            timeFilter.getDurationOperation(),
                            timeFilter.getPrimaryDuration(),
                            timeFilter.getSecondaryDuration(),
                            attribute,
                            subAttribute
                    )
            );
        }

        return timeSpecifications;
    }

    /*
     * Utility method that maps attributes of a FeedbackFilter to their respective Specification handler
     * compareValues() method handles `productivity` requests since the filtering supports before, after, equals or during operations
     * findValueIn() method handles `distractions` requests as users can retrieve sessions with different distractions
     */
    private static Specification<Session> buildFeedbackSpecifications(FeedbackFilter feedbackFilter) {

        String attribute = "feedback";
        Specification<Session> feedbackSpecifications = Specification.where(null);

        if (feedbackFilter.getProductivityOperation() != null) {
            String subAttribute = "productivity";
            feedbackSpecifications = feedbackSpecifications.and(
                    SpecificationsBuilder.compareValues(
                            feedbackFilter.getProductivityOperation(),
                            feedbackFilter.getPrimaryProductivity(),
                            feedbackFilter.getSecondaryProductivity(),
                            attribute,
                            subAttribute
                    )
            );
        }

        if (feedbackFilter.getDistractions() != null) {
            String subAttribute = "distraction";
            feedbackSpecifications = feedbackSpecifications.and(
                    SpecificationsBuilder.findValueIn(
                            feedbackFilter.getDistractions(),
                            attribute,
                            subAttribute));
        }
        return feedbackSpecifications;
    }
}
