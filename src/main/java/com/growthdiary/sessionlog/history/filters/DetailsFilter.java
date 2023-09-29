package com.growthdiary.sessionlog.history.filters;

import com.growthdiary.sessionlog.tracker.details.Details;
import com.growthdiary.sessionlog.tracker.session.Session;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

public class DetailsFilter {

    /**
     * Creates a Specification that retrieves sessions based on Details-related filter requests
     * @param filter FilterRequest object containing the filter criteria for Details properties
     * @return a Specification object that filters sessions based on the specified Details criteria
     */
    public static Specification<Session> buildWith(FilterRequest filter) {

        Specification<Session> filteredSessions = Specification.where(null);
        String key = filter.getProperty();

        // calls appropriate methods based on the property assigned
        if (key.equals("skill")) {
            filteredSessions = findSkillIn(filter.getSkills());

        } else if (key.equals("description")) {
            filteredSessions = findDescriptionsLike(filter.getDescriptions().get(0));
        }
        return filteredSessions;
    }

    private static Specification<Session> findSkillIn(List<String> skillList) {
        return (root, query, criteriaBuilder) -> {

            Join<Session, Details> detailsJoin = root.join("details");
            List<Predicate> skillPredicates = new ArrayList<>();
            for (String skill : skillList) {
                Predicate predicate = criteriaBuilder.equal(detailsJoin.get("skill"), skill);
                skillPredicates.add(predicate);
            }
            // convert ArrayList into an array of predicates since cb.or only takes in Predicates
            // utilised ArrayList from the start since its dynamic, while regular array cannot be appended
            return criteriaBuilder.or(skillPredicates.toArray(new Predicate[0]));
        };
    }

    private static Specification<Session> findDescriptionsLike(String givenDescription) {
        return (root, query, criteriaBuilder) -> {
            Join<Session, Details> detailsJoin = root.join("details");
            return criteriaBuilder.like(detailsJoin.get("description"), "%"+givenDescription+"%");
        };
    }
}
