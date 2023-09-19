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

    private final TimeRepository timeRepository;

    @Autowired
    public TimeService(TimeRepository timeRepository) {
        this.timeRepository = timeRepository;
    }

    /**
     * Takes in user input to create a SessionTime object
     * @param startDate the first day of the user session
     * @param endDate the last day of the user session
     * @param startTime time that user session started
     * @param endTime time that user session ended
     * @return SessionTime object
     */
    public Time createTime(LocalDate startDate,
                           LocalDate endDate,
                           LocalTime startTime,
                           LocalTime endTime) {

        Time sessionTime = new Time();

        sessionTime.setStartDate(startDate);
        sessionTime.setEndDate(endDate);
        sessionTime.setStartTime(startTime);
        sessionTime.setEndTime(endTime);

        Long duration = sessionTime.calculateDuration();
        sessionTime.setDuration(duration);

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
