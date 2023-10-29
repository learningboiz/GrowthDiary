package com.growthdiary.sessionlog.sessionhistory.unit.validators;

import com.growthdiary.sessionlog.history.dtos.FilterRequestDTO;
import com.growthdiary.sessionlog.history.historyfilter.DetailsFilter;
import com.growthdiary.sessionlog.history.historyfilter.FeedbackFilter;
import com.growthdiary.sessionlog.history.historyfilter.TimeFilter;
import com.growthdiary.sessionlog.history.validators.FilterRequestDTOValidator;
import org.junit.jupiter.api.Test;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.Errors;

import static org.junit.jupiter.api.Assertions.*;

public class FilterRequestValidationUnitTests {

    private final FilterRequestDTOValidator filterRequestDTOValidator = new FilterRequestDTOValidator();

    @Test
    public void validRequestShouldNotHaveErrors() {
        DetailsFilter validDetails = new DetailsFilter.BuildFilter()
                .findDescriptionLike("API")
                .build();

        TimeFilter validTime = new TimeFilter.BuildFilter()
                .findDurationsBetween(10L, 80L)
                .build();

        FeedbackFilter validFeedback = new FeedbackFilter.BuildFilter()
                .findProductivityBetween(2, 5)
                .build();

        FilterRequestDTO filterRequestDTO = new FilterRequestDTO(validDetails, validTime, validFeedback);

        Errors errors = new BeanPropertyBindingResult(filterRequestDTO, "filterRequestDTO");
        filterRequestDTOValidator.validate(filterRequestDTO, errors);
        assertFalse(errors.hasErrors());
    }

    @Test
    public void invalidRequestsShouldHaveErrors() {
        DetailsFilter invalidDetails = new DetailsFilter.BuildFilter()
                .findDescriptionLike(null)
                .build();

        TimeFilter invalidTime = new TimeFilter.BuildFilter()
                .findDurationsBetween(100L, 40L)
                .build();

        FeedbackFilter invalidFeedback = new FeedbackFilter.BuildFilter()
                .findProductivityBetween(-100, -200)
                .build();

        FilterRequestDTO invalidRequestDTO = new FilterRequestDTO(invalidDetails, invalidTime, invalidFeedback);

        Errors errors = new BeanPropertyBindingResult(invalidRequestDTO, "invalidRequestDTO");
        filterRequestDTOValidator.validate(invalidRequestDTO, errors);
        assertTrue(errors.hasErrors());
        assertEquals(3, errors.getErrorCount());
    }
}
