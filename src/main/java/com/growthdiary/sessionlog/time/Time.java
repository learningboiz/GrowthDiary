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

    private LocalTime startTime;

    private LocalDate endDate;


    private LocalTime endTime;

    private Long duration;

    /* Default no-argument constructor required by Hibernate
     * Used during database queries
     */
    public Time() {}

    public Time(LocalDateTime startPeriod, LocalDateTime endPeriod) {
        this.startDate = startPeriod.toLocalDate();
        this.startTime = startPeriod.toLocalTime();
        this.endDate = endPeriod.toLocalDate();
        this.endTime = endPeriod.toLocalTime();

        this.duration = calculateDuration(startPeriod, endPeriod);
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getStartDate() {
        return this.startDate;
    }

    public void setStartTime(LocalTime startTime) {
        this.startTime = startTime;
    }

    public LocalTime getStartTime() {
        return this.startTime;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public LocalDate getEndDate() {
        return this.endDate;
    }

    public void setEndTime(LocalTime endTime) {
        this.endTime = endTime;
    }

    public LocalTime getEndTime() {
        return this.endTime;
    }

    public void setDuration(Long duration) {
        this.duration = duration;
    }

    public Long getDuration() {
        return this.duration;
    }

    /**
     * Returns the duration of session in minutes
     * @return duration in minutes
     */
    public Long calculateDuration(LocalDateTime startPeriod, LocalDateTime endPeriod) {
        return ChronoUnit.MINUTES.between(startPeriod, endPeriod);
    }
}
