package com.growthdiary.sessionlog.sessionhistory.unit.builders;

import com.growthdiary.sessionlog.history.historyfilter.DetailsFilter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class DetailsFilterUnitTests {

    private List<String> skills;
    private String description;

    @BeforeEach
    public void createDummyValues() {

        skills = Arrays.asList("Java", "Django");
        description = "tests";
    }

    @Test
    public void createBothSkillAndDescription() {

        DetailsFilter detailsFilter = new DetailsFilter.BuildFilter()
                .findSkillIn(skills)
                .findDescriptionLike(description)
                .build();

        assertEquals(detailsFilter.getSkills(), skills);
        assertEquals(detailsFilter.getDescription(), description);
    }

    @Test
    public void createOnlySkill() {

        DetailsFilter justSkillsFilter = new DetailsFilter.BuildFilter()
                .findSkillIn(skills)
                .build();

        assertEquals(justSkillsFilter.getSkills(), skills);
        assertNull(justSkillsFilter.getDescription());
    }

    @Test
    public void createOnlyDescription() {

        DetailsFilter justDescriptionsFilter = new DetailsFilter.BuildFilter()
                .findDescriptionLike(description)
                .build();

        assertEquals(justDescriptionsFilter.getDescription(), description);
        assertNull(justDescriptionsFilter.getSkills());
    }

    @Test
    public void filterTakesTheLatestArgument() {

        List<String> extraSkills = Arrays.asList("MySQL", "React");
        List<String> latestSkills = Arrays.asList("Python", "HTML");

        String extraDescription = "API";
        String latestDescription = "tutorials";

        DetailsFilter detailsFilter = new DetailsFilter.BuildFilter()
                .findDescriptionLike(description)
                .findSkillIn(skills)
                .findDescriptionLike(extraDescription)
                .findDescriptionLike(latestDescription)
                .findSkillIn(extraSkills)
                .findSkillIn(latestSkills)
                .build();

        assertEquals(latestSkills, detailsFilter.getSkills());
        assertEquals(latestDescription, detailsFilter.getDescription());
    }
}
