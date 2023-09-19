package com.growthdiary.sessionlog.time;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;

/**
 * Main service to handle the creation and management of session time details
 */
@Service
public class TimeService {

    private final TimeRepository sessionTimeRepository;

    @Autowired
    public TimeService(TimeRepository sessionTimeRepository) {
        this.sessionTimeRepository = sessionTimeRepository;
    }

    /**
     * Takes in user input to create a SessionTime object
     * @param firstDate the first day of the user session
     * @param lastDate the last day of the user session
     * @param startTime time that user session started
     * @param endTime time that user session ended
     * @return SessionTime object
     */
    public Time createTime(LocalDate firstDate,
                           LocalDate lastDate,
                           LocalTime startTime,
                           LocalTime endTime) {

        Time sessionTime = new Time();

        sessionTime.setFirstDate(firstDate);
        sessionTime.setLastDate(lastDate);
        sessionTime.setStartTime(startTime);
        sessionTime.setEndTime(endTime);

        Long duration = sessionTime.calculateDuration();
        sessionTime.setDuration(duration);

        sessionTimeRepository.save(sessionTime);
        return sessionTime;
    }
}