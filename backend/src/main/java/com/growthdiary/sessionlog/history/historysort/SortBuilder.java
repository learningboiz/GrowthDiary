package com.growthdiary.sessionlog.history.historysort;

import com.growthdiary.sessionlog.history.requests.SortRequest;
import org.springframework.data.domain.Sort;

/**
 * The {@code SortBuilder} class provides a method for building Spring Data {@link Sort} objects.
 * It simplifies the creation of sorting criteria by building the appropriate Sort object based on the property and direction provided.
 *
 * @see SortDirection
 * @see SortRequest
 * @see Sort
 */
public class SortBuilder {

    public static Sort buildSort(String property, SortDirection sortDirection) {
        if (sortDirection == SortDirection.ASC) {
            return Sort.by(property).ascending();
        } else {
            return Sort.by(property).descending();
        }
    }
}
