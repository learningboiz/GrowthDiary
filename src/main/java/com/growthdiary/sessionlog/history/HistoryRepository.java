package com.growthdiary.sessionlog.history;

import com.growthdiary.sessionlog.tracker.models.Session;
import com.growthdiary.sessionlog.tracker.SessionRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

/**
 * Extends SessionRepository to decouple responsibilities
 * Main purpose for this repository is to implement the session history use case
 */
@Repository
public interface HistoryRepository extends SessionRepository, JpaSpecificationExecutor<Session> {
}
