package com.growthdiary.sessionlog.history.specifications;

import com.growthdiary.sessionlog.history.FilterOperators;
import com.growthdiary.sessionlog.history.FilterRequest;
import com.growthdiary.sessionlog.tracker.session.Session;
import com.growthdiary.sessionlog.tracker.time.Time;
import jakarta.persistence.criteria.Join;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDate;

public class TimeSpecifications {

    public static Specification<Session> createTimeSpec(FilterRequest filter) {
            Specification<Session> timeSpec = Specification.where(null);

            FilterOperators operator = filter.getOperator();
            String key = filter.getKey();

            if (key.equals("duration")) {

                Long primaryDuration = filter.getDurations().get(0);
                Long secondaryDuration = filter.getDurations().get(1);

                switch (operator) {
                    case EQUALS -> {
                        timeSpec = durationEquals(primaryDuration);
                    }
                    case LESS_THAN -> {
                        timeSpec = durationUnder(primaryDuration);
                    }
                    case GREATER_THAN -> {
                        timeSpec = durationAbove(primaryDuration);
                    }
                    case BETWEEN -> {
                        timeSpec = durationBetween(primaryDuration, secondaryDuration);
                    }
                }

            } else if (key.equals("startDate")) {

                LocalDate primaryDate = filter.getDates().get(0);
                LocalDate secondaryDate = filter.getDates().get(1);

                switch (operator) {
                    case EQUALS -> {
                        timeSpec = dateEquals(primaryDate);
                    }
                    case LESS_THAN -> {
                        timeSpec = dateBefore(primaryDate);
                    }
                    case GREATER_THAN -> {
                        timeSpec = dateAfter(primaryDate);
                    }
                    case BETWEEN -> {
                        timeSpec = dateBetween(primaryDate, secondaryDate);
                    }
                }
            }
            return timeSpec;
        }

    private static Specification<Session> durationEquals(Long givenDuration) {
        return (root, query, criteriaBuilder) -> {
            Join<Session, Time> timeJoin = root.join("time");
            return criteriaBuilder.equal(timeJoin.get("duration"), givenDuration);
        };
    }

    private static Specification<Session> durationUnder(Long givenDuration) {
        return (root, query, criteriaBuilder) -> {
            Join<Session, Time> timeJoin = root.join("time");
            return criteriaBuilder.lt(timeJoin.get("duration"), givenDuration);
        };
    }

    private static Specification<Session> durationAbove(Long givenDuration) {
        return (root, query, criteriaBuilder) -> {
            Join<Session, Time> timeJoin = root.join("time");
            return criteriaBuilder.gt(timeJoin.get("duration"), givenDuration);
        };
    }

    private static Specification<Session> durationBetween(Long startValue, Long endValue) {
        return (root, query, criteriaBuilder) -> {
            Join<Session, Time> timeJoin = root.join("time");
            return criteriaBuilder.between(timeJoin.get("duration"), startValue, endValue);
        };
    }

    private static Specification<Session> dateEquals(LocalDate givenDate) {
        return (root, query, criteriaBuilder) -> {
            Join<Session, Time> timeJoin = root.join("time");
            return criteriaBuilder.equal(timeJoin.get("startDate"), givenDate);
        };
    }

    private static Specification<Session> dateBefore(LocalDate givenDate) {
        return (root, query, criteriaBuilder) -> {
            Join<Session, Time> timeJoin = root.join("time");
            return criteriaBuilder.lessThan(timeJoin.get("startDate"), givenDate);
        };
    }

    private static Specification<Session> dateAfter(LocalDate givenDate) {
        return (root, query, criteriaBuilder) -> {
            Join<Session, Time> timeJoin = root.join("time");
            return criteriaBuilder.greaterThan(timeJoin.get("startDate"), givenDate);
        };
    }

    private static Specification<Session> dateBetween(LocalDate startValue, LocalDate endValue) {
        return (root, query, criteriaBuilder) -> {
            Join<Session, Time> timeJoin = root.join("time");
            return criteriaBuilder.between(timeJoin.get("startDate"), startValue, endValue);
        };
    }
}
