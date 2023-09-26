package com.growthdiary.sessionlog.history.specifications;

import com.growthdiary.sessionlog.tracker.feedback.Feedback;
import com.growthdiary.sessionlog.history.FilterOperators;
import com.growthdiary.sessionlog.history.FilterRequest;
import com.growthdiary.sessionlog.tracker.session.Session;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

public class FeedbackSpecifications {

    public static Specification<Session> createFeedbackSpec(FilterRequest filterRequest) {
        Specification<Session> feedbackSpecs = Specification.where(null);
        String key = filterRequest.getKey();
        if (key.equals("productivity")) {

            Integer primaryVal = filterRequest.getProductivity().get(0);
            Integer secondaryVal = filterRequest.getProductivity().get(1);
            FilterOperators operator = filterRequest.getOperator();
            switch (operator) {
                case EQUALS -> {
                    feedbackSpecs = productivityEquals(primaryVal);
                }
                case GREATER_THAN -> {
                    feedbackSpecs = productivityAbove(primaryVal);

                }
                case LESS_THAN -> {
                    feedbackSpecs = productivityUnder(primaryVal);
                }
                case BETWEEN -> {
                    feedbackSpecs = productivityBetween(primaryVal, secondaryVal);
                }
            }
        } else if (key.equals("distraction")) {
            feedbackSpecs = distractionIn(filterRequest.getDistractions());
        }
        return feedbackSpecs;
    }

    private static Specification<Session> productivityEquals(Integer givenProductivity) {
        return (root, query, criteriaBuilder) -> {
            Join<Session, Feedback> feedbackJoin = root.join("feedback");
            return criteriaBuilder.equal(feedbackJoin.get("productivity"), givenProductivity);
        };
    }

    private static Specification<Session> productivityUnder(Integer givenProductivity) {
        return (root, query, criteriaBuilder) -> {
            Join<Session, Feedback> feedbackJoin = root.join("feedback");
            return criteriaBuilder.lt(feedbackJoin.get("productivity"), givenProductivity);
        };
    }

    private static Specification<Session> productivityAbove(Integer givenProductivity) {
        return (root, query, criteriaBuilder) -> {
            Join<Session, Feedback> feedbackJoin = root.join("feedback");
            return criteriaBuilder.gt(feedbackJoin.get("productivity"), givenProductivity);
        };
    }

    private static Specification<Session> productivityBetween(Integer startValue, Integer endValue) {
        return (root, query, criteriaBuilder) -> {
            Join<Session, Feedback> feedbackJoin = root.join("feedback");
            return criteriaBuilder.between(feedbackJoin.get("productivity"), startValue, endValue);
        };
    }

    private static Specification<Session> distractionIn(List<String> distractionList) {
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
