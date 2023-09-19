package com.growthdiary.sessionlog.time;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

/**
 * Main service to handle the creation and management of session time details
 */
@Service
public class TimeService {

    private final TimeRepository timeRepository;

    @Autowired
    public TimeService(TimeRepository timeRepository) {
        this.timeRepository = timeRepository;
    }

    /**
     * Creates a Time object based on user input
     * @param startPeriod Date and time at which session was started
     * @param endPeriod Date and time at which session was ended
     * @return Time object
     */
    public Time createTime(LocalDateTime startPeriod, LocalDateTime endPeriod) {

        Time sessionTime = new Time(startPeriod, endPeriod);
        return sessionTime;
    }

    /**
     * Takes in a Time object and persist it into the database
     * @param time Time object
     */
    public void saveTime(Time time) {
        timeRepository.save(time);
    }
}
