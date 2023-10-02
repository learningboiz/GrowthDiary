package com.growthdiary.sessionlog.history.filters;

import com.growthdiary.sessionlog.history.requests.FilterRequest;
import com.growthdiary.sessionlog.tracker.session.Session;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDate;
import java.util.List;

public class FilterMapper {

    /**
     * Creates a Specification object that combines multiple specifications for each filter
     * @param filterRequests List containing filter requests
     * @return a Specification object
     */
    public static Specification<Session> getFilteredSessions(List<FilterRequest> filterRequests) {

        /*
        Create a base/wrapper specification object to combine other specifications
         */
        Specification<Session> compiledSpecs = Specification.where(null);

        /*
        Iterate through the list of filter requests
        Each filter is mapped to the appropriate helper function based on the given entity
        Exception thrown if the entity provided does not match the existing models
         */
        for (FilterRequest request : filterRequests) {

            String entity = request.getEntity();

            switch (entity) {
                case "details" -> {
                    Specification<Session> detailsSpec = filterByDetails(request);
                    compiledSpecs = compiledSpecs.and(detailsSpec);
                }
                case "time" -> {
                    Specification<Session> timeSpec = filterByTime(request);
                    compiledSpecs = compiledSpecs.and(timeSpec);
                }
                case "feedback" -> {
                    Specification<Session> feedbackSpec = filterByFeedback(request);
                    compiledSpecs = compiledSpecs.and(feedbackSpec);
                }
                default -> throw new IllegalArgumentException("Entity must be 'details', 'time' or 'feedback'");
            }
        }
        return compiledSpecs;
    }

    private static Specification<Session> filterByDetails(FilterRequest filterRequest) {

        Specification<Session> filteredSessions = Specification.where(null);

        String entity = filterRequest.getEntity();
        String property = filterRequest.getProperty();

        if (property.equals("skill")) {
            List<String> skills = filterRequest.getSkills();
            filteredSessions = FilterSpecifications.findValueIn(skills, entity, property);

        } else if (property.equals("description")) {
            String firstValue = filterRequest.getDescriptions().get(0);
            filteredSessions = FilterSpecifications.findValuesLike(firstValue, entity, property);

        } else throw new RuntimeException("Property provided must be either 'skill' or 'description'");

        return filteredSessions;
    }

    private static Specification<Session> filterByTime(FilterRequest filterRequest) {

        Specification<Session> filteredSessions = Specification.where(null);
        String entity = filterRequest.getEntity();
        String property = filterRequest.getProperty();

        if (property.equals("duration")) {
            Long firstValue = filterRequest.getDurations().get(0);
            Long secondValue = filterRequest.getDurations().get(1);
            FilterOperations operation = filterRequest.getOperator();

            filteredSessions = FilterSpecifications.compareValues(operation, firstValue, secondValue, entity, property);
        } else if (property.equals("startDate")) {
            LocalDate firstValue = filterRequest.getDates().get(0);
            LocalDate secondValue = filterRequest.getDates().get(1);
            FilterOperations operation = filterRequest.getOperator();

            filteredSessions = FilterSpecifications.compareValues(operation, firstValue, secondValue, entity, property);
        } else throw new RuntimeException("Property provided must be either 'duration' or 'startDate'");
        return filteredSessions;
    }

    private static Specification<Session> filterByFeedback(FilterRequest filterRequest) {
        Specification<Session> filteredSessions = Specification.where(null);
        String entity = filterRequest.getEntity();
        String property = filterRequest.getProperty();

        if (property.equals("productivity")) {
            int firstValue = filterRequest.getProductivity().get(0);
            int secondValue = filterRequest.getProductivity().get(1);
            FilterOperations operation = filterRequest.getOperator();

            filteredSessions = FilterSpecifications.compareValues(operation, firstValue, secondValue, entity, property);
        } else if (property.equals("distraction")) {
            List<String> distractions = filterRequest.getDistractions();
            filteredSessions = FilterSpecifications.findValueIn(distractions, entity, property);
        } else throw new RuntimeException("Property provided must be either 'productivity' or 'distraction'");
        return filteredSessions;
    }
}
