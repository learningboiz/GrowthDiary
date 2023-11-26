package com.growthdiary.sessionlog.sessionhistory.unit.validators;

import com.growthdiary.sessionlog.history.historyfilter.FeedbackFilter;
import com.growthdiary.sessionlog.history.historyfilter.FilterOperations;
import com.growthdiary.sessionlog.history.validators.FeedbackFilterValidator;
import org.junit.jupiter.api.Test;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.Errors;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class FeedbackFilterValidationUnitTests {

    private final FeedbackFilterValidator feedbackFilterValidator = new FeedbackFilterValidator();

    @Test
    public void validFilterReturnsNoErrors() {

        FeedbackFilter feedbackFilter = new FeedbackFilter.BuildFilter()
                .findObstacleIn(Arrays.asList("Social media, Streaming services"))
                .findProductivityBetween(2,4)
                .build();

        Errors errors = new BeanPropertyBindingResult(feedbackFilter, "feedbackFilter");
        feedbackFilterValidator.validate(feedbackFilter, errors);
        assertFalse(errors.hasErrors());;
    }

    @Test
    public void bothAttributesCannotBeNull() {

        FeedbackFilter nullAttributes = new FeedbackFilter.BuildFilter()
                .findObstacleIn(null)
                .findProductivity(null, null)
                .build();

        Errors errors = new BeanPropertyBindingResult(nullAttributes, "nullAttributes");
        feedbackFilterValidator.validate(nullAttributes, errors);
        assertTrue(errors.hasErrors());
        assertEquals(1, errors.getErrorCount());
    }

    @Test
    public void distractionsListCannotBeEmpty() {

        FeedbackFilter emptyDistractions = new FeedbackFilter.BuildFilter()
                .findObstacleIn(List.of())
                .build();

        Errors errors = new BeanPropertyBindingResult(emptyDistractions, "emptyDistractions");
        feedbackFilterValidator.validate(emptyDistractions, errors);
        assertTrue(errors.hasErrors());
        assertEquals(1, errors.getErrorCount());
    }

    @Test
    public void primaryDurationMustBeProvided() {

        FeedbackFilter missingPrimaryValue = new FeedbackFilter.BuildFilter()
                .findProductivity(FilterOperations.GREATER_THAN, null)
                .build();

        Errors errors = new BeanPropertyBindingResult(missingPrimaryValue, "missingPrimaryValue");
        feedbackFilterValidator.validate(missingPrimaryValue, errors);
        assertTrue(errors.hasErrors());
        assertEquals(1, errors.getErrorCount());
    }

    @Test
    public void betweenFilterRequiresTwoValues() {

        FeedbackFilter missingSecondaryValue = new FeedbackFilter.BuildFilter()
                .findProductivityBetween(2, null)
                .build();

        Errors errors = new BeanPropertyBindingResult(missingSecondaryValue, "missingSecondaryValue");
        feedbackFilterValidator.validate(missingSecondaryValue, errors);
        assertTrue(errors.hasErrors());
        assertEquals(1, errors.getErrorCount());
    }

    @Test
    public void firstValueMustBeLessThanSecond() {

        FeedbackFilter valuesIncorrectOrder = new FeedbackFilter.BuildFilter()
                .findProductivityBetween(5, 1)
                .build();

        Errors errors = new BeanPropertyBindingResult(valuesIncorrectOrder, "valuesIncorrectOrder");
        feedbackFilterValidator.validate(valuesIncorrectOrder, errors);
        assertTrue(errors.hasErrors());
        assertEquals(1, errors.getErrorCount());
    }

    @Test
    public void productivityValuesMustBeInRange() {

        FeedbackFilter valuesOutOfRange = new FeedbackFilter.BuildFilter()
                .findProductivityBetween(-33, 40)
                .build();

        Errors errors = new BeanPropertyBindingResult(valuesOutOfRange, "valuesOutOfRange");
        feedbackFilterValidator.validate(valuesOutOfRange, errors);
        assertTrue(errors.hasErrors());
        assertEquals(1, errors.getErrorCount());
    }
}
