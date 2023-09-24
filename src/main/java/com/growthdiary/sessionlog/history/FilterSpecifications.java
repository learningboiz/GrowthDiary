package com.growthdiary.sessionlog.history;

import com.growthdiary.sessionlog.details.Details;
import com.growthdiary.sessionlog.feedback.Feedback;
import com.growthdiary.sessionlog.session.Session;
import com.growthdiary.sessionlog.time.Time;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class FilterSpecifications {

    public static Specification<Session> joinTables() {
        return (root, query, criteriaBuilder) -> {
            Join<Session, Details> detailsJoin = root.join("details");
            Join<Session, Time> timeJoin = root.join("time");
            Join<Session, Feedback> feedbackJoin = root.join("feedback");

            query.multiselect(root, detailsJoin, timeJoin, feedbackJoin);
            return query.getRestriction();
        };
    }

    public static Specification<Session> skillIs(List<String> skillList) {
        return (root, query, criteriaBuilder) -> {

            Join<Session, Details> detailsJoin = root.join("details");
            List<Predicate> skillPredicates = new ArrayList<>();
            for (String skill : skillList) {
                Predicate predicate = criteriaBuilder.equal(detailsJoin.get("skill"), skill);
                skillPredicates.add(predicate);
            }
            return criteriaBuilder.or(skillPredicates.toArray(new Predicate[0]));
        };
    }

    public static Specification<Session> descriptionLike(String descriptionLike) {
        return (root, query, criteriaBuilder) -> {
            Join<Session, Details> detailsJoin = root.join("details");
            return criteriaBuilder.like(detailsJoin.get("description"), "%"+descriptionLike+"%");
        };
    }

    public static Specification<Session> durationEqualsUnderAbove(FilterOperators operator, Long duration) {
        return (root, query, criteriaBuilder) -> {
            Join<Session, Time> timeJoin = root.join("time");

            switch (operator) {
                case EQUALS -> {
                    return criteriaBuilder.equal(timeJoin.get("duration"), duration);
                }
                case GREATER_THAN -> {
                    return criteriaBuilder.gt(timeJoin.get("duration"), duration);
                }
                case LESS_THAN -> {
                    return criteriaBuilder.lt(timeJoin.get("duration"), duration);
                }
                default -> throw new RuntimeException("Operation not supported");
            }
        };
    }

    public static Specification<Session> durationBetween(Long startValue, Long endValue) {
        return (root, query, criteriaBuilder) -> {
            Join<Session, Time> timeJoin = root.join("time");
            return criteriaBuilder.between(timeJoin.get("duration"), startValue, endValue);
        };
    }

    public static Specification<Session> dateEqualsBeforeAfter(FilterOperators operator, LocalDate specifiedDate) {
        return (root, query, criteriaBuilder) -> {
            Join<Session, Time> timeJoin = root.join("time");

            switch (operator) {
                case EQUALS -> {
                    return criteriaBuilder.equal(timeJoin.get("startDate"), specifiedDate);
                }
                case GREATER_THAN -> {
                    return criteriaBuilder.greaterThan(timeJoin.get("startDate"), specifiedDate);
                }
                case LESS_THAN -> {
                    return criteriaBuilder.lessThan(timeJoin.get("startDate"), specifiedDate);
                }
                default -> throw new RuntimeException("Operation not supported");
            }
        };
    }

    public static Specification<Session> dateBetween(LocalDate startValue, LocalDate endValue) {
        return (root, query, criteriaBuilder) -> {
            Join<Session, Time> timeJoin = root.join("time");
            return criteriaBuilder.between(timeJoin.get("startDate"), startValue, endValue);
        };
    }


}
