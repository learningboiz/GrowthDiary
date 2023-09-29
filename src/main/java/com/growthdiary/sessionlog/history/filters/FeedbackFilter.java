package com.growthdiary.sessionlog.history.filters;

import com.growthdiary.sessionlog.tracker.feedback.Feedback;
import com.growthdiary.sessionlog.tracker.session.Session;
import com.growthdiary.sessionlog.tracker.time.Time;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

public class FeedbackFilter {

    /**
     * Creates a Specification that retrieves sessions based on Feedback-related filter requests
     * @param filter FilterRequest object containing the filter criteria for Feedback properties
     * @return a Specification object that filters sessions based on the specified Feedback criteria
     */
    public static Specification<Session> buildWith(FilterRequest filter) {

        Specification<Session> filteredSessions = Specification.where(null);
        String key = filter.getProperty();

        if (key.equals("productivity")) {

            Integer primaryVal = filter.getProductivity().get(0);
            Integer secondaryVal = filter.getProductivity().get(1);
            FilterOperators operator = filter.getOperator();

            filteredSessions = compareProductivity(operator, primaryVal, secondaryVal);

        } else if (key.equals("distraction")) {
            filteredSessions = findDistractionsIn(filter.getDistractions());
        }
        return filteredSessions;
    }

    private static Specification<Session> compareProductivity(FilterOperators operator, Integer primaryValue, Integer secondaryValue) {
        return (root, query, criteriaBuilder) -> {
            Join<Session, Time> timeJoin = root.join("feedback");
            switch (operator) {
                case EQUALS -> {
                    return criteriaBuilder.equal(timeJoin.get("productivity"), primaryValue);
                }
                case LESS_THAN -> {
                    return criteriaBuilder.lessThan(timeJoin.get("productivity"), primaryValue);
                }
                case GREATER_THAN -> {
                    return criteriaBuilder.greaterThan(timeJoin.get("productivity"), primaryValue);
                }
                case BETWEEN -> {
                    return criteriaBuilder.between(timeJoin.get("productivity"), primaryValue, secondaryValue);
                }
                default -> throw new RuntimeException("The given operator is not valid");
            }
        };
    }

    private static Specification<Session> findDistractionsIn(List<String> distractionList) {
        return (root, query, criteriaBuilder) -> {

            Join<Session, Feedback> feedbackJoin = root.join("feedback");
            List<Predicate> distractionPredicates = new ArrayList<>();
            for (String distraction : distractionList) {
                Predicate predicate = criteriaBuilder.equal(feedbackJoin.get("distraction"), distraction);
                distractionPredicates.add(predicate);
            }
            // convert ArrayList into an array of predicates since cb.or only takes in Predicates
            // utilised ArrayList from the start since its dynamic, while regular array cannot be appended
            return criteriaBuilder.or(distractionPredicates.toArray(new Predicate[0]));
        };
    }
}
