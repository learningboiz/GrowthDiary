package com.growthdiary.sessionlog.history;

import com.growthdiary.sessionlog.details.Details;
import com.growthdiary.sessionlog.feedback.Feedback;
import com.growthdiary.sessionlog.session.Session;
import com.growthdiary.sessionlog.time.Time;
import jakarta.persistence.criteria.Join;
import org.springframework.data.jpa.domain.Specification;

public class HistorySpecifications {

    public static Specification<Session> joinTables() {
        return (root, query, criteriaBuilder) -> {
            Join<Session, Details> detailsJoin = root.join("details");
            Join<Session, Time> timeJoin = root.join("time");
            Join<Session, Feedback> feedbackJoin = root.join("feedback");

            query.multiselect(root, detailsJoin, timeJoin, feedbackJoin);
            return query.getRestriction();
        };
    }


}
