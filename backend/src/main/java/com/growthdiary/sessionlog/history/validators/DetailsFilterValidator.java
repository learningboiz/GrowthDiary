package com.growthdiary.sessionlog.history.validators;

import com.growthdiary.sessionlog.history.historyfilter.DetailsFilter;
import jakarta.validation.constraints.NotNull;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.List;

public class DetailsFilterValidator implements Validator {

    @Override
    public boolean supports(@NotNull Class clazz) {
        return DetailsFilter.class.equals(clazz);
    }

    @Override
    public void validate(@NotNull Object target, @NotNull Errors errors) {

        DetailsFilter detailsFilter = (DetailsFilter) target;

        List<String> givenSkills = detailsFilter.getSkills();
        String givenDescription = detailsFilter.getDescription();

        if ((givenSkills == null || givenSkills.isEmpty()) && givenDescription == null) {
            errors.reject("detailsFilter.attributes.null", "Details filter must specify skills, description or both");
        }
    }
}
