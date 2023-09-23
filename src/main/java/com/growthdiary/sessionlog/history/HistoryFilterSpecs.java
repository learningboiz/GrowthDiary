package com.growthdiary.sessionlog.history;

import com.growthdiary.sessionlog.details.Details;
import com.growthdiary.sessionlog.feedback.Feedback;
import com.growthdiary.sessionlog.session.Session;
import com.growthdiary.sessionlog.time.Time;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

public class HistoryFilterSpecs {

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
}
