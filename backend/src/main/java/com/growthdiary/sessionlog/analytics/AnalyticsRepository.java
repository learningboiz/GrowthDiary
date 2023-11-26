package com.growthdiary.sessionlog.analytics;

import com.growthdiary.sessionlog.tracker.models.Session;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface AnalyticsRepository extends CrudRepository<Session, Long> {

    @Query(value = "SELECT " +
            "(SELECT topic " +
            "FROM Session " +
            "WHERE start_date >= :minRange " +
            "AND start_date <= :maxRange " +
            "GROUP BY topic " +
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


    @Query(value = "SELECT " +
            "CASE " +
            "WHEN duration < 30 THEN 'Under 30 minutes' " +
            "WHEN duration >= 30 AND duration <= 60 THEN 'Between 30 and 60 minutes' " +
            "WHEN duration > 60 AND duration <= 120 THEN 'Between 1 and 2 hours' " +
            "ELSE 'Over 2 hours' " +
            "END AS attributeCategory, " +
            "AVG(productivity) AS averageProductivity " +
            "FROM Session " +
            "GROUP BY attributeCategory " +
            "ORDER BY averageProductivity DESC ", nativeQuery = true)
    List<ProductivityChart> getDurationCorrelation();

    @Query(value = "SELECT " +
            "CASE " +
            "WHEN start_time >= '06:00' AND start_time <= '12:00' THEN 'Between 6AM and 12PM' " +
            "WHEN start_time > '12:00' AND start_time <= '18:00' THEN 'Between 12PM and 6PM' " +
            "WHEN start_time > '18:00' AND start_time <= '00:00' THEN 'Between 6PM and Midnight' " +
            "ELSE 'Between Midnight and 6AM' " +
            "END AS attributeCategory, " +
            "AVG(productivity) AS averageProductivity " +
            "FROM Session " +
            "GROUP BY attributeCategory " +
            "ORDER BY averageProductivity DESC ", nativeQuery = true)
    List<ProductivityChart> getTimeCorrelation();

    @Query(value = "SELECT " +
            "obstacle AS attributeCategory, " +
            "AVG(productivity) AS averageProductivity " +
            "FROM Session " +
            "GROUP BY attributeCategory " +
            "ORDER BY averageProductivity DESC ", nativeQuery = true)
    List<ProductivityChart> getObstacleCorrelation();
}
