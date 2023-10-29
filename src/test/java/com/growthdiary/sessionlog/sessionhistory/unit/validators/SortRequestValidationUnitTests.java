package com.growthdiary.sessionlog.sessionhistory.unit.validators;

import com.growthdiary.sessionlog.history.dtos.SortRequestDTO;
import com.growthdiary.sessionlog.history.historysort.SortDirection;
import com.growthdiary.sessionlog.history.validators.SortRequestDTOValidator;
import org.junit.jupiter.api.Test;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.Errors;

import static org.junit.jupiter.api.Assertions.*;

public class SortRequestValidationUnitTests {

    private final SortRequestDTOValidator sortRequestDTOValidator = new SortRequestDTOValidator();

    private final String property = "time.startDate";
    private final SortDirection direction = SortDirection.DESC;

    @Test
    public void sortWithBothPropertyAndDirectionIsValid() {

        SortRequestDTO sortRequestDTO = new SortRequestDTO(property, direction);

        Errors errors = new BeanPropertyBindingResult(sortRequestDTO, "sortRequestDTO");
        sortRequestDTOValidator.validate(sortRequestDTO, errors);
        assertFalse(errors.hasErrors());
    }

    @Test
    public void sortWithoutPropertyIsInvalid() {

        SortRequestDTO missingPropertyDTO = new SortRequestDTO(null, direction);

        Errors errors = new BeanPropertyBindingResult(missingPropertyDTO, "missingPropertyDTO");
        sortRequestDTOValidator.validate(missingPropertyDTO, errors);
        assertTrue(errors.hasErrors());
        assertEquals(1, errors.getErrorCount());
    }

    @Test
    public void sortWithoutDirectionIsInvalid() {

        SortRequestDTO missingDirectionDTO = new SortRequestDTO(property, null);

        Errors errors = new BeanPropertyBindingResult(missingDirectionDTO, "missingDirectionDTO");
        sortRequestDTOValidator.validate(missingDirectionDTO, errors);
        assertTrue(errors.hasErrors());
        assertEquals(1, errors.getErrorCount());
    }

    @Test
    public void sortPropertyInputNotInValidList() {

        SortRequestDTO invalidPropertyInput = new SortRequestDTO("goldfish", direction);

        Errors errors = new BeanPropertyBindingResult(invalidPropertyInput, "invalidPropertyInput");
        sortRequestDTOValidator.validate(invalidPropertyInput, errors);
        assertTrue(errors.hasErrors());
        assertEquals(1, errors.getErrorCount());
    }
}
