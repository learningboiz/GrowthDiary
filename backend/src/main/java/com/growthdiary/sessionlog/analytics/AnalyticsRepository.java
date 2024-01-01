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
            "(SELECT topic FROM Session GROUP BY topic ORDER BY COUNT(topic) DESC LIMIT 1) AS topTopic, " +
            "(SELECT obstacle FROM Session GROUP BY obstacle ORDER BY COUNT(obstacle) DESC LIMIT 1) AS topObstacle, " +
            "SUM(duration) AS totalDuration, " +
            "AVG(duration) AS avgDuration, " +
            "AVG(productivity) AS avgProductivity " +
            "FROM Session", nativeQuery = true)
    SessionStats getSummary();


    @Query(value = "SELECT " +
            "CASE " +
            "WHEN duration < 30 THEN 'Under 30 minutes' " +
            "WHEN duration >= 30 AND duration <= 60 THEN 'Between 30 and 60 minutes' " +
            "WHEN duration > 60 AND duration <= 120 THEN 'Between 1 and 2 hours' " +
            "ELSE 'Over 2 hours' " +
            "END AS category, " +
            "AVG(productivity) AS averageProductivity " +
            "FROM Session " +
            "GROUP BY category " +
            "ORDER BY averageProductivity DESC ", nativeQuery = true)
    List<ProductivityChart> getDurationCorrelation();

    @Query(value = "SELECT " +
            "CASE " +
            "WHEN start_time >= '06:00' AND start_time <= '12:00' THEN 'Between 6AM and 12PM' " +
            "WHEN start_time > '12:00' AND start_time <= '18:00' THEN 'Between 12PM and 6PM' " +
            "WHEN start_time > '18:00' AND start_time <= '00:00' THEN 'Between 6PM and Midnight' " +
            "ELSE 'Between Midnight and 6AM' " +
            "END AS category, " +
            "AVG(productivity) AS averageProductivity " +
            "FROM Session " +
            "GROUP BY category " +
            "ORDER BY averageProductivity DESC ", nativeQuery = true)
    List<ProductivityChart> getTimeCorrelation();

    @Query(value = "SELECT " +
            "obstacle AS category, " +
            "AVG(productivity) AS averageProductivity " +
            "FROM Session " +
            "GROUP BY category " +
            "ORDER BY averageProductivity DESC ", nativeQuery = true)
    List<ProductivityChart> getObstacleCorrelation();
}
