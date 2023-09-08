package com.growthdiary.sessionlog.sessiontime;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;

/**
 * Main entity to represent the dates, timing and duration of a session
 */
@Entity
public class SessionTime {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    private LocalDate firstDate;

    private LocalDate lastDate;

    private LocalTime startTime;

    private LocalTime endTime;

    private Long duration;

    public SessionTime() {
    }

    public void setFirstDate(LocalDate firstDate) {
        this.firstDate = firstDate;
    }

    public void setLastDate(LocalDate lastDate) {
        this.lastDate = lastDate;
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

    public LocalDate getFirstDate() {
        return this.firstDate;
    }

    public LocalDate getLastDate() {
        return this.lastDate;
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
    public Long calculateDuration() {
        LocalDateTime start = LocalDateTime.of(this.firstDate, this.startTime);
        LocalDateTime end = LocalDateTime.of(this.lastDate, this.endTime);

        return ChronoUnit.MINUTES.between(start, end);
    }
}
