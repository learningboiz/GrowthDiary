package com.growthdiary.sessionlog.history.specifications;

import com.growthdiary.sessionlog.history.historyfilter.DetailsFilter;
import com.growthdiary.sessionlog.history.historyfilter.FeedbackFilter;
import com.growthdiary.sessionlog.history.dtos.FilterRequestDTO;
import com.growthdiary.sessionlog.history.historyfilter.TimeFilter;
import com.growthdiary.sessionlog.tracker.session.Session;
import org.springframework.data.jpa.domain.Specification;

/**
 * The {@code SpecificationsMapper} class provides methods that maps attributes of a FilterRequestDTO
 * to their respective Specifications Builder.
 *
 * @see SpecificationsBuilder
 * @see FilterRequestDTO
 */
public class SpecificationsMapper {

    /**
     * Builds a Specification used to filter sessions based on the requested attributes
     *
     * @param filterRequest Requests for specific Session attributes including Details, Time and Feedback
     * @return A specification to return sessions that match the request criteria
     */
    public static Specification<Session> getAllSpecifications(FilterRequestDTO filterRequest) {

        Specification<Session> allSpecifications = Specification.where(null);

        if (filterRequest.getDetailsFilter() != null) {
            allSpecifications = allSpecifications.and(filterByDetails(filterRequest.getDetailsFilter()));
        }

        if (filterRequest.getTimeFilter() != null) {
            allSpecifications = allSpecifications.and(filterByTime(filterRequest.getTimeFilter()));
        }

        if (filterRequest.getFeedbackFilter() != null) {
            allSpecifications = allSpecifications.and(filterByFeedback(filterRequest.getFeedbackFilter()));
        }
        return allSpecifications;
    }

    /*
     * Utility method that maps attributes of a DetailsFilter to their respective Specification handler
     * findValueIn() method handles `skills` requests as users can retrieve sessions focusing on different skills
     * findValuesLike() method handles `description requests as users can only request one word/phrase to match
     */
    private static Specification<Session> filterByDetails(DetailsFilter detailsFilter) {

        Specification<Session> detailsSpecs = Specification.where(null);

        if (detailsFilter.getSkills() != null) {
            detailsSpecs = detailsSpecs.and(
                    SpecificationsBuilder.findValueIn(
                    detailsFilter.getSkills(),
                    "details",
                    "skill"
                    )
            );
        }

        if (detailsFilter.getDescription() != null) {
            detailsSpecs = detailsSpecs.and(
                    SpecificationsBuilder.findValuesLike(
                            detailsFilter.getDescription(),
                            "details",
                            "skill"
                    )
            );
        }

        return detailsSpecs;
    }

    /*
     * Utility method that maps attributes of a TimeFilter to their respective Specification handler
     * compareValues() method handles these attributes since the filtering supports before, after, equals or during operations
     */
    private static Specification<Session> filterByTime(TimeFilter timeFilter) {

        Specification<Session> timeSpecs = Specification.where(null);

        if (timeFilter.getDateOperation() != null) {
            timeSpecs = timeSpecs.and(
                    SpecificationsBuilder.compareValues(
                            timeFilter.getDateOperation(),
                            timeFilter.getPrimaryDate(),
                            timeFilter.getSecondaryDate(),
                            "time",
                            "startDate"
                    )
            );
        }

        if (timeFilter.getDurationOperation() != null) {
            timeSpecs = timeSpecs.and(
                    SpecificationsBuilder.compareValues(
                            timeFilter.getDurationOperation(),
                            timeFilter.getPrimaryDuration(),
                            timeFilter.getSecondaryDuration(),
                            "time",
                            "duration"
                    )
            );
        }

        return timeSpecs;
    }

    /*
     * Utility method that maps attributes of a FeedbackFilter to their respective Specification handler
     * compareValues() method handles `productivity` requests since the filtering supports before, after, equals or during operations
     * findValueIn() method handles `distractions` requests as users can retrieve sessions with different distractions
     */
    private static Specification<Session> filterByFeedback(FeedbackFilter feedbackFilter) {

        Specification<Session> feedbackSpecs = Specification.where(null);

        if (feedbackFilter.getProductivityOperation() != null) {
            feedbackSpecs = feedbackSpecs.and(
                    SpecificationsBuilder.compareValues(
                            feedbackFilter.getProductivityOperation(),
                            feedbackFilter.getPrimaryProductivity(),
                            feedbackFilter.getSecondaryProductivity(),
                            "feedback",
                            "productivity"
                    )
            );
        }

        if (feedbackFilter.getDistractions() != null) {
            feedbackSpecs = feedbackSpecs.and(
                    SpecificationsBuilder.findValueIn(
                            feedbackFilter.getDistractions(),
                            "feedback",
                            "distractions"
                    )
            );
        }
        return feedbackSpecs;
    }
}
