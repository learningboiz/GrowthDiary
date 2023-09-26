package com.growthdiary.sessionlog.tracker.time;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

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
     * Creates a Time object and saves it into the database
     * @param startPeriod Date and time at which session was started
     * @param endPeriod Date and time at which session was ended
     * @return Time object
     */
    public Time createTime(LocalDateTime startPeriod, LocalDateTime endPeriod) {

        Time sessionTime = new Time(startPeriod, endPeriod);
        return timeRepository.save(sessionTime);
    }
}
