package com.growthdiary.sessionlog.history.requests;

import org.springframework.data.domain.Sort;

import java.util.Arrays;
import java.util.List;

/**
 * Main class that manages the creation of sort queries based on selected properties
 *
 * <p>Supports sorting the following properties:</p>
 * <ul>
 *     <li>skill</li>
 *     <li>duration</li>
 *     <li>startDate</li>
 *     <li>productivity</li>
 * </ul>
 */
public class SortRequest {

    public static Sort sortByAscending(String property) {
        validateProperty(property);
        return Sort.by(property).ascending();
    }

    public static Sort sortByDescending(String property) {
        validateProperty(property);
        return Sort.by(property).descending();
    }

    private static void validateProperty(String property) {
        List<String> validProperties = Arrays.asList("skill", "duration", "startDate", "productivity");
        if (!validProperties.contains(property)) {
            throw new IllegalArgumentException("Only skill, duration, startDate and productivity can be sorted");
        }
    }
}
