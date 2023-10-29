package com.growthdiary.sessionlog.sessionhistory.unit.validators;

import com.growthdiary.sessionlog.history.historyfilter.DetailsFilter;
import com.growthdiary.sessionlog.history.validators.DetailsFilterValidator;
import org.junit.jupiter.api.Test;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.Errors;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.List;

public class DetailsFilterValidationUnitTests {

    private final DetailsFilterValidator detailsFilterValidator = new DetailsFilterValidator();

    @Test
    public void validFilterReturnsNoErrors() {

        DetailsFilter detailsFilter = new DetailsFilter.BuildFilter()
                .findSkillIn(Arrays.asList("Java", "Spring Boot"))
                .findDescriptionLike("web application")
                .build();

        Errors errors = new BeanPropertyBindingResult(detailsFilter, "detailsFilter");
        detailsFilterValidator.validate(detailsFilter, errors);
        assertFalse(errors.hasErrors());
    }

    @Test
    public void bothAttributesCannotBeNull() {

        DetailsFilter invalidDetailsFilter = new DetailsFilter.BuildFilter()
                .findSkillIn(null)
                .findDescriptionLike(null)
                .build();

        Errors errors = new BeanPropertyBindingResult(invalidDetailsFilter, "invalidDetailsFilter");
        detailsFilterValidator.validate(invalidDetailsFilter, errors);
        assertTrue(errors.hasErrors());
        assertEquals(1, errors.getErrorCount());
    }

    @Test
    public void skillListCannotBeEmpty() {

        DetailsFilter emptySkills = new DetailsFilter.BuildFilter()
                .findSkillIn(List.of())
                .build();

        Errors errors = new BeanPropertyBindingResult(emptySkills, "emptySkills");
        detailsFilterValidator.validate(emptySkills, errors);
        assertTrue(errors.hasErrors());
        assertEquals(1, errors.getErrorCount());
    }
}
