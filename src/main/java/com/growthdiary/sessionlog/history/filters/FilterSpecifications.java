package com.growthdiary.sessionlog.history.filters;

import com.growthdiary.sessionlog.history.filters.FilterOperations;
import com.growthdiary.sessionlog.tracker.session.Session;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

public class FilterSpecifications {

    public static <T> Specification<Session> findValueIn(List<T> values, String entity, String property) {
        return (root, query, criteriaBuilder) -> {

            List<Predicate> predicates = new ArrayList<>();
            for (T value : values) {
                Predicate predicate = criteriaBuilder.equal(root.join(entity).get(property), value);
                predicates.add(predicate);
            }

            // convert ArrayList into an array of predicates since cb.or only takes in Predicates
            // utilised ArrayList from the start since its dynamic, while regular array cannot be appended
            return criteriaBuilder.or(predicates.toArray(new Predicate[0]));
        };
    }

    public static <T> Specification<Session> findValuesLike(T value, String entity, String property) {
        return (root, query, criteriaBuilder) -> {
            return criteriaBuilder.like(root.join(entity).get(property), "%"+value+"%");
        };
    }

    public static <T extends Comparable<T>> Specification<Session> compareValues(FilterOperations operation,
                                                           T firstValue,
                                                           T secondValue,
                                                           String entity,
                                                           String property) {
        return (root, query, criteriaBuilder) -> {
            switch (operation) {
                case EQUALS -> {
                    return criteriaBuilder.equal(root.join(entity).get(property), firstValue);
                }
                case GREATER_THAN -> {
                    return criteriaBuilder.greaterThan(root.join(entity).get(property), firstValue);
                }
                case LESS_THAN -> {
                    return criteriaBuilder.lessThan(root.join(entity).get(property), firstValue);
                }
                case BETWEEN -> {
                    return criteriaBuilder.between(root.join(entity).get(property), firstValue, secondValue);
                }
                default -> throw new RuntimeException("Provided operation must be EQUALS, GREATER_THAN, LESS_THAN OR BETWEEN");
            }
        };
    }
}
