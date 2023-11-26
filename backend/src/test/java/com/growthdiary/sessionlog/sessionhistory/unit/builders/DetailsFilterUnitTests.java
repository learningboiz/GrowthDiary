package com.growthdiary.sessionlog.sessionhistory.unit.builders;

import com.growthdiary.sessionlog.history.historyfilter.DetailsFilter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class DetailsFilterUnitTests {

    private List<String> topics;
    private String description;

    @BeforeEach
    public void createDummyValues() {

        topics = Arrays.asList("Java", "Django");
        description = "tests";
    }

    @Test
    public void createBothSkillAndDescription() {

        DetailsFilter detailsFilter = new DetailsFilter.BuildFilter()
                .findTopicIn(topics)
                .findDescriptionLike(description)
                .build();

        assertEquals(detailsFilter.getTopics(), topics);
        assertEquals(detailsFilter.getDescription(), description);
    }

    @Test
    public void createOnlySkill() {

        DetailsFilter justSkillsFilter = new DetailsFilter.BuildFilter()
                .findTopicIn(topics)
                .build();

        assertEquals(justSkillsFilter.getTopics(), topics);
        assertNull(justSkillsFilter.getDescription());
    }

    @Test
    public void createOnlyDescription() {

        DetailsFilter justDescriptionsFilter = new DetailsFilter.BuildFilter()
                .findDescriptionLike(description)
                .build();

        assertEquals(justDescriptionsFilter.getDescription(), description);
        assertNull(justDescriptionsFilter.getTopics());
    }

    @Test
    public void filterTakesTheLatestArgument() {

        List<String> extraTopics = Arrays.asList("MySQL", "React");
        List<String> latestTopics = Arrays.asList("Python", "HTML");

        String extraDescription = "API";
        String latestDescription = "tutorials";

        DetailsFilter detailsFilter = new DetailsFilter.BuildFilter()
                .findDescriptionLike(description)
                .findTopicIn(topics)
                .findDescriptionLike(extraDescription)
                .findDescriptionLike(latestDescription)
                .findTopicIn(extraTopics)
                .findTopicIn(latestTopics)
                .build();

        assertEquals(latestTopics, detailsFilter.getTopics());
        assertEquals(latestDescription, detailsFilter.getDescription());
    }
}
