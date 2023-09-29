package com.growthdiary.sessionlog.history.filters;

import com.growthdiary.sessionlog.tracker.session.Session;
import com.growthdiary.sessionlog.tracker.time.Time;
import jakarta.persistence.criteria.Join;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDate;

public class TimeFilter {

    /**
     * Creates a Specification that retrieves sessions based on Time-related filter requests
     * @param filter FilterRequest object containing the filter criteria for Time properties
     * @return a Specification object that filters sessions based on the specified Time criteria
     */
    public static Specification<Session> buildWith(FilterRequest filter) {
        Specification<Session> filteredSessions = Specification.where(null);

        FilterOperators operator = filter.getOperator();
        String key = filter.getProperty();

        // calls appropriate methods based on the property assigned

        if (key.equals("duration")) {

            Long primaryValue = filter.getDurations().get(0);
            Long secondaryValue = filter.getDurations().get(1);

            filteredSessions = byComparingDurations(operator, primaryValue, secondaryValue);

        } else if (key.equals("startDate")) {

            LocalDate primaryDate = filter.getDates().get(0);
            LocalDate secondaryDate = filter.getDates().get(1);

            filteredSessions = byComparingDates(operator, primaryDate, secondaryDate);
        }
        return filteredSessions;
    }

    private static Specification<Session> byComparingDurations(FilterOperators operator, Long primaryValue, Long secondaryValue) {
        return (root, query, criteriaBuilder) -> {
            Join<Session, Time> timeJoin = root.join("time");
            switch (operator) {
                case EQUALS -> {
                    return criteriaBuilder.equal(timeJoin.get("duration"), primaryValue);
                }
                case LESS_THAN -> {
                    return criteriaBuilder.lessThan(timeJoin.get("duration"), primaryValue);
                }
                case GREATER_THAN -> {
                    return criteriaBuilder.greaterThan(timeJoin.get("duration"), primaryValue);
                }
                case BETWEEN -> {
                    return criteriaBuilder.between(timeJoin.get("duration"), primaryValue, secondaryValue);
                }
                default -> throw new RuntimeException("The given operator is not valid");
            }
        };
    }

    private static Specification<Session> byComparingDates(FilterOperators operator, LocalDate primaryValue, LocalDate secondaryValue) {
        return (root, query, criteriaBuilder) -> {
            Join<Session, Time> timeJoin = root.join("time");
            switch (operator) {
                case EQUALS -> {
                    return criteriaBuilder.equal(timeJoin.get("startDate"), primaryValue);
                }
                case LESS_THAN -> {
                    return criteriaBuilder.lessThan(timeJoin.get("startDate"), primaryValue);
                }
                case GREATER_THAN -> {
                    return criteriaBuilder.greaterThan(timeJoin.get("startDate"), primaryValue);
                }
                case BETWEEN -> {
                    return criteriaBuilder.between(timeJoin.get("startDate"), primaryValue, secondaryValue);
                }
                default -> throw new RuntimeException("The given operator is not valid");
            }
        };
    }
}