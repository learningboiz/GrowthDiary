package com.growthdiary.sessionlog.history.specifications;

import com.growthdiary.sessionlog.history.historyfilter.FilterOperations;
import com.growthdiary.sessionlog.tracker.models.Session;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

/**
 * The {@code SpecificationsBuilder} class provides methods for creating custom Spring JPA Specifications.
 *
 * @see SpecificationsMapper
 */
public class SpecificationsBuilder {

    public static <T> Specification<Session> findValueIn(List<T> values, String attribute, String subAttribute) {
        return (root, query, criteriaBuilder) -> {

            List<Predicate> predicates = new ArrayList<>();
            for (T value : values) {
                Predicate predicate = criteriaBuilder.equal(root.get(attribute).get(subAttribute), value);
                predicates.add(predicate);
            }

            /*
             * The code below converts the ArrayList into an array of predicates. This was done because
             * CriteriaBuilder only takes in Predicates as an argument, hence the need to convert the original
             * ArrayList.
             *
             * An ArrayList was used in the first place over a fixed array because this method requires values to
             * be added dynamically depending on the input list size.
             */
            return criteriaBuilder.or(predicates.toArray(new Predicate[0]));
        };
    }

    public static <T> Specification<Session> findValuesLike(T value, String attribute, String subAttribute) {
        return (root, query, criteriaBuilder) -> {

            return criteriaBuilder.like(root.get(attribute).get(subAttribute), "%"+value+"%");
        };
    }

    public static <T extends Comparable<T>> Specification<Session> compareValues(FilterOperations operation,
                                                                                 T firstValue,
                                                                                 T secondValue,
                                                                                 String attribute,
                                                                                 String subAttribute) {
        return (root, query, criteriaBuilder) -> {

            switch (operation) {
                case EQUALS -> {
                    return criteriaBuilder.equal(root.get(attribute).get(subAttribute), firstValue);
                }
                case GREATER_THAN -> {
                    return criteriaBuilder.greaterThan(root.get(attribute).get(subAttribute), firstValue);
                }
                case LESS_THAN -> {
                    return criteriaBuilder.lessThan(root.get(attribute).get(subAttribute), firstValue);
                }
                case BETWEEN -> {
                    return criteriaBuilder.between(root.get(attribute).get(subAttribute), firstValue, secondValue);
                }
                default -> throw new IllegalArgumentException("Provided operation must be EQUALS, GREATER_THAN, LESS_THAN OR BETWEEN");
            }
        };
    }
}
