package com.growthdiary.sessionlog.analytics;

import com.growthdiary.sessionlog.tracker.models.Session;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@Repository
public interface AnalyticsRepository extends CrudRepository<Session, Long> {

    @Query(value = "SELECT " +
            "(SELECT skill " +
            "FROM Session " +
            "WHERE start_date >= :minRange " +
            "AND start_date <= :maxRange " +
            "GROUP BY skill " +
            "ORDER BY SUM(duration) DESC " +
            "LIMIT 1) AS topSkill, " +
            "SUM(duration) AS totalDuration, " +
            "SUM(productivity) / 5 / :numDays AS avgProductivity " +
            "FROM Session " +
            "WHERE start_date >= :minRange " +
            "AND start_date <= :maxRange ", nativeQuery = true)
    WeeklySummary getSummary(@Param("minRange") LocalDate minRange,
                             @Param("maxRange") LocalDate maxRange,
                             @Param("numDays") Long numDays);
}
