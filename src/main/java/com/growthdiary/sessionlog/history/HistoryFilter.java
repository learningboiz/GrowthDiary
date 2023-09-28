package com.growthdiary.sessionlog.history;

import com.growthdiary.sessionlog.tracker.session.Session;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

public class HistoryFilter<T extends Comparable<T>> {

    public HistoryFilter() {
    }

    public Specification<Session> createFilters(List<FilterRequest<T>> filterRequests) {
        Specification<Session> specifications = Specification.where(null);

        for (FilterRequest<T> request: filterRequests) {
            FilterOperators operator = request.getOperator();
            String entity = request.getEntity();
            String property = request.getProperty();
            List<T> values = request.getValues();

            if (operator == FilterOperators.IN) {
                specifications = specifications.and(byIn(entity, property, values));
            } else if (operator == FilterOperators.LIKE) {
                specifications = specifications.and(byLike(entity,property, values));
            } else {
                specifications = specifications.and(byComparison(operator, entity, property, values));
            }
        }
        return specifications;
    }

    private Specification<Session> byIn(String entity, String property, List<T> values) {
        return (root, query, criteriaBuilder) -> {

            List<Predicate> predicates = new ArrayList<>();
            for(T value : values) {
                Predicate predicate = criteriaBuilder.equal(root.join(entity).get(property), value);
                predicates.add(predicate);
            }
            return criteriaBuilder.or(predicates.toArray(predicates.toArray(new Predicate[0])));
        };
    }

    private Specification<Session> byLike(String entity, String property, List<T> values) {
        return (root, query, criteriaBuilder) -> {

            T value = values.get(0);
            return criteriaBuilder.like(root.join(entity).get(property), "%" + value + "%");
        };
    }

    private Specification<Session> byComparison(FilterOperators operator, String entity, String property, List<T> values) {
        return (root, query, criteriaBuilder) -> {

            T primaryValue = values.get(0);
            T secondaryValue = values.get(1);

            switch(operator) {
                case EQUALS -> {
                    return criteriaBuilder.equal(root.join(entity).get(property), primaryValue);
                }
                case GREATER_THAN -> {
                    return criteriaBuilder.greaterThan(root.join(entity).get(property), primaryValue);
                }
                case LESS_THAN ->  {
                    return criteriaBuilder.lessThan(root.join(entity).get(property), primaryValue);
                }
                case BETWEEN -> {
                    return criteriaBuilder.between(root.join(entity).get(property), primaryValue, secondaryValue);
                }
            }
            return null;
        };
    }
}