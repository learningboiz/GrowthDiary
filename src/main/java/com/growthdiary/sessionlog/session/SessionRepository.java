package com.growthdiary.sessionlog.session;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/** Extended JpaRepository as it provides CRUD and Pagination functionalities */
@Repository
public interface SessionRepository extends JpaRepository<Session, Long> {
}
