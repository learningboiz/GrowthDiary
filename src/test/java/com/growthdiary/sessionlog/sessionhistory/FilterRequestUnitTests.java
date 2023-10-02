package com.growthdiary.sessionlog.sessionhistory;

import com.growthdiary.sessionlog.history.filters.FilterOperations;
import com.growthdiary.sessionlog.history.requests.FilterRequest;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class FilterRequestUnitTests {

    @Test
    public void testSkillFilterRequest() {
        List<String> skills = new ArrayList<>();
        String sampleOne = "Spring Boot";
        String sampleTwo = "Hashmaps";
        skills.add(sampleOne);
        skills.add(sampleTwo);

        FilterRequest request = new FilterRequest.Builder()
                .filterBySkill(skills)
                .build();

        assertEquals("details", request.getEntity());
        assertEquals("skill", request.getProperty());
        assertEquals(skills, request.getSkills());
        assertNull(request.getProductivity());
        assertNull(request.getDates());
    }

    @Test
    public void testMultipleFiltersPerRequest() {
        List<String> descriptions = new ArrayList<>();
        descriptions.add("web app");

        List<Integer> productivity = new ArrayList<>();
        productivity.add(3);

        assertThrows(UnsupportedOperationException.class, () -> {
            FilterRequest request = new FilterRequest.Builder()
                    .filterByDescription(descriptions)
                    .filterByProductivity(productivity, FilterOperations.EQUALS)
                    .build();
        });
    }

    @Test
    public void testEmptyRequest() {
        assertThrows(UnsupportedOperationException.class, () -> {
            FilterRequest request = new FilterRequest.Builder().build();
        });
    }
}
