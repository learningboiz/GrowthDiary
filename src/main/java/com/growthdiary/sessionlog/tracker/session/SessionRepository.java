package com.growthdiary.sessionlog.tracker.session;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/** Extended JpaRepository as it provides CRUD and Pagination functionalities */
@Repository
public interface SessionRepository extends JpaRepository<Session, Long> {
}
