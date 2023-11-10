package com.growthdiary.sessionlog.sessionhistory.unit.validators;

import com.growthdiary.sessionlog.history.historyfilter.FilterOperations;
import com.growthdiary.sessionlog.history.historyfilter.TimeFilter;
import com.growthdiary.sessionlog.history.validators.TimeFilterValidator;
import org.junit.jupiter.api.Test;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.Errors;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

public class TimeFilterValidationUnitTests {

    private final TimeFilterValidator timeFilterValidator = new TimeFilterValidator();

    @Test
    public void validFilterReturnsNoErrors() {

        TimeFilter timeFilter = new TimeFilter.BuildFilter()
                .findDates(FilterOperations.GREATER_THAN, LocalDate.of(2023, 10, 5))
                .findDurationsBetween(20L, 30L)
                .build();

        Errors errors = new BeanPropertyBindingResult(timeFilter, "timeFilter");
        timeFilterValidator.validate(timeFilter, errors);
        assertFalse(errors.hasErrors());
    }

    @Test
    public void bothAttributesCannotBeNull() {

        TimeFilter nullDates = new TimeFilter.BuildFilter()
                .findDates(null, null)
                .findDuration(null, null)
                .build();

        Errors errors = new BeanPropertyBindingResult(nullDates, "nullDates");
        timeFilterValidator.validate(nullDates, errors);
        assertTrue(errors.hasErrors());
        assertEquals(1, errors.getErrorCount());
    }

    @Test
    public void primaryDateMustBeProvided() {

        TimeFilter missingDate = new TimeFilter.BuildFilter()
                .findDates(FilterOperations.GREATER_THAN, null)
                .build();

        Errors errors = new BeanPropertyBindingResult(missingDate, "missingDate");
        timeFilterValidator.validate(missingDate, errors);
        assertTrue(errors.hasErrors());
        assertEquals(1, errors.getErrorCount());
    }

    @Test
    public void primaryDurationMustBeProvided() {

        TimeFilter missingDuration = new TimeFilter.BuildFilter()
                .findDuration(FilterOperations.EQUALS, null)
                .build();

        Errors errors = new BeanPropertyBindingResult(missingDuration, "missingDuration");
        timeFilterValidator.validate(missingDuration, errors);
        assertTrue(errors.hasErrors());
        assertEquals(1, errors.getErrorCount());
    }

    @Test
    public void dateRangeMustHaveTwoValues() {

        TimeFilter missingSecondDate = new TimeFilter.BuildFilter()
                .findDatesBetween(LocalDate.of(2023, 10, 5), null)
                .build();

        Errors errors = new BeanPropertyBindingResult(missingSecondDate, "missingSecondDate");
        timeFilterValidator.validate(missingSecondDate, errors);
        assertTrue(errors.hasErrors());
        assertEquals(1, errors.getErrorCount());
    }

    @Test
    public void durationRangeMustHaveTwoValues() {
        TimeFilter missingSecondDuration = new TimeFilter.BuildFilter()
                .findDurationsBetween(40L, null)
                .build();

        Errors errors = new BeanPropertyBindingResult(missingSecondDuration, "missingSecondDuration");
        timeFilterValidator.validate(missingSecondDuration, errors);
        assertTrue(errors.hasErrors());
        assertEquals(1, errors.getErrorCount());
    }

    @Test
    public void startDateMustComeBeforeEndDate() {
        LocalDate startDate = LocalDate.of(2023, 10,5);
        LocalDate endDate = LocalDate.of(2023,7,5); // start date 3 months after end date

        TimeFilter startAfterEnd = new TimeFilter.BuildFilter()
                .findDatesBetween(startDate, endDate)
                .build();

        Errors errors = new BeanPropertyBindingResult(startAfterEnd, "startAfterEnd");
        timeFilterValidator.validate(startAfterEnd, errors);
        assertTrue(errors.hasErrors());
        assertEquals(1, errors.getErrorCount());
    }

    @Test
    public void firstDurationMustBeShorterThanSecond() {
        TimeFilter firstGreaterThanSecond = new TimeFilter.BuildFilter()
                .findDurationsBetween(10L, 5L)
                .build();

        Errors errors = new BeanPropertyBindingResult(firstGreaterThanSecond, "firstGreaterThanSecond");
        timeFilterValidator.validate(firstGreaterThanSecond, errors);
        assertTrue(errors.hasErrors());
        assertEquals(1, errors.getErrorCount());
    }

    @Test
    public void durationsMustBePositive() {
        TimeFilter negativeDurations = new TimeFilter.BuildFilter()
                .findDurationsBetween(-50L, -80L)
                .build();

        Errors errors = new BeanPropertyBindingResult(negativeDurations, "negativeDurations");
        timeFilterValidator.validate(negativeDurations, errors);
        assertTrue(errors.hasErrors());
        assertEquals(1, errors.getErrorCount());
    }
}
