package com.growthdiary.sessionlog.time;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
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

    private LocalDate startDate;

    private LocalDate endDate;

    private LocalTime startTime;

    private LocalTime endTime;

    private Long duration;

    public Time(LocalDateTime startPeriod, LocalDateTime endPeriod) {
        this.startDate = startPeriod.toLocalDate();
        this.endDate = endPeriod.toLocalDate();

        this.startTime = startPeriod.toLocalTime();
        this.endTime = endPeriod.toLocalTime();

        this.duration = calculateDuration(startPeriod, endPeriod);
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public void setStartTime(LocalTime startTime) {
        this.startTime = startTime;
    }

    public void setEndTime(LocalTime endTime) {
        this.endTime = endTime;
    }

    public void setDuration(Long duration) {
        this.duration = duration;
    }

    public LocalDate getStartDate() {
        return this.startDate;
    }

    public LocalDate getEndDate() {
        return this.endDate;
    }

    public LocalTime getStartTime() {
        return this.startTime;
    }

    public LocalTime getEndTime() {
        return this.endTime;
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
