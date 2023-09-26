package com.growthdiary.sessionlog.history.specifications;

import com.growthdiary.sessionlog.tracker.details.Details;
import com.growthdiary.sessionlog.history.FilterRequest;
import com.growthdiary.sessionlog.tracker.session.Session;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

public class DetailsSpecifications {

    public static Specification<Session> createDetailsSpec(FilterRequest filterRequest) {
        Specification<Session> detailsSpecs = Specification.where(null);
        String key = filterRequest.getKey();
        if (key.equals("skill")) {
            detailsSpecs = skillIn(filterRequest.getSkills());

        } else if (key.equals("description")) {
            detailsSpecs = descriptionLike(filterRequest.getDescriptions().get(0));
        }
        return detailsSpecs;
    }

    private static Specification<Session> skillIn(List<String> skillList) {
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

    private static Specification<Session> descriptionLike(String givenDescription) {
        return (root, query, criteriaBuilder) -> {
            Join<Session, Details> detailsJoin = root.join("details");
            return criteriaBuilder.like(detailsJoin.get("description"), "%"+givenDescription+"%");
        };
    }
}
