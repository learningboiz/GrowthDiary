package com.growthdiary.sessionlog.history;

import com.growthdiary.sessionlog.tracker.models.Session;
import com.growthdiary.sessionlog.tracker.SessionRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface HistoryRepository extends JpaRepository<Session, Long>, JpaSpecificationExecutor<Session> {
}
