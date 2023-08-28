package com.growthdiary.sessionlog.session;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/** Extended JpaRepository as it provides methods for both CRUD and pagination */
@Repository
public interface SessionRepository extends JpaRepository<Session, Long> {
}

