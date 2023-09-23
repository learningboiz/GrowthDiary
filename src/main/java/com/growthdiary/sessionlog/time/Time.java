package com.growthdiary.sessionlog.time;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

/**
 * Main entity to represent the dates, timing and duration of a session
 */
@Entity
public class Time {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    private LocalDateTime startPeriod;

    private LocalDateTime endPeriod;

    private Long duration;

    /* Default no-argument constructor required by Hibernate
     * Used during database queries
     */
    public Time() {}

    public Time(LocalDateTime startPeriod, LocalDateTime endPeriod) {
        this.startPeriod = startPeriod;
        this.endPeriod = endPeriod;

        this.duration = calculateDuration(startPeriod, endPeriod);
    }

    public void setStartPeriod(LocalDateTime startPeriod) {
        this.startPeriod = startPeriod;
    }

    public void setEndDate(LocalDateTime endPeriod) {
        this.endPeriod = endPeriod;
    }

    public void setDuration(Long duration) {
        this.duration = duration;
    }

    public LocalDateTime getStartPeriod() {
        return this.startPeriod;
    }

    public LocalDateTime getEndPeriod() {
        return this.endPeriod;
    }

    public Long getDuration() {
        return this.duration;
    }

    /**
     * Calculates how long a user spent on a session
     * @return Long object representing the session duration in minutes
     */
    public Long calculateDuration(LocalDateTime startPeriod, LocalDateTime endPeriod) {
        return ChronoUnit.MINUTES.between(startPeriod, endPeriod);
    }
}
