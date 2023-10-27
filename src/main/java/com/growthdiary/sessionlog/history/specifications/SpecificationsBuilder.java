package com.growthdiary.sessionlog.history.filters;

import com.growthdiary.sessionlog.tracker.session.Session;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

/**
 * The {@code FilterSpecifications} class provides methods for creating custom Spring JPA Specifications.
 * These Specifications are used to query and retrieve Sessions that match the requested filters.
 * @see FilterMapper
 */
public class FilterSpecifications {

    /**
     * Builds a Specification that filters entries where the specified property's value matches any of the values in the given list
     * @param values A list of values to match against
     * @param entity Name of entity to join
     * @param property Name of property to filter from
     * @return Specification object with Session entries that matches any of the requested values
     * @param <T> The type of values in the list
     */
    public static <T> Specification<Session> findValueIn(List<T> values, String entity, String property) {
        return (root, query, criteriaBuilder) -> {

            List<Predicate> predicates = new ArrayList<>();
            for (T value : values) {
                Predicate predicate = criteriaBuilder.equal(root.join(entity).get(property), value);
                predicates.add(predicate);
            }

            /*
             * The code below converts the ArrayList into an array of predicates
             * CriteriaBuilder only takes in Predicates as an argument, hence the need for the conversion
             * ArrayList was utilised since it allows for the dynamic addition of values, compared to the fixed regular array
             */
            return criteriaBuilder.or(predicates.toArray(new Predicate[0]));
        };
    }

    /**
     * Builds a Specification that filters entries where the specified property's value partially matches the value provided
     * @param value A value to match against
     * @param entity Name of entity to join
     * @param property Name of property to filter from
     * @return Specification object with Session entries that matches or partially matches the requested value
     * @param <T> The type of value to match against
     */
    public static <T> Specification<Session> findValuesLike(T value, String entity, String property) {
        return (root, query, criteriaBuilder) -> {
            return criteriaBuilder.like(root.join(entity).get(property), "%"+value+"%");
        };
    }

    /**
     * Builds a Specification that filters entries where the specified property's value are compared with requested values
     * @param firstValue The first value for comparison
     * @param secondValue The second value for comparison (required for BETWEEN operations)
     * @param entity Name of entity to join
     * @param property Name of property to filter from
     * @return Specification object with Session entries that matches the requested value comparisons
     * @param <T> The type of value to compare with
     * @throws IllegalArgumentException Exception thrown if invalid operation is provided
     */
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
                default -> throw new IllegalArgumentException("Provided operation must be EQUALS, GREATER_THAN, LESS_THAN OR BETWEEN");
            }
        };
    }
}
