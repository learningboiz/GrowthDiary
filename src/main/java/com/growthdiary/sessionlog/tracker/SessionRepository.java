package com.growthdiary.sessionlog.tracker;

import com.growthdiary.sessionlog.tracker.models.Session;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/*
 * Extended JpaRepository as it provides CRUD and Pagination functionality used for the Tracker and History module respectively
 */
@Repository
public interface SessionRepository extends JpaRepository<Session, Long> {
}
