package com.growthdiary.sessionlog.history.specifications;

import com.growthdiary.sessionlog.history.historyfilter.FilterRequestDTO;

/**
 * The {@code FilterOperations} enum represents the valid operations that can be used when filtering session attributes.
 * These operations define how values can be compared.
 * @see FilterRequestDTO
 */
public enum FilterOperations {
    
    EQUALS,
    LESS_THAN,
    GREATER_THAN,
    BETWEEN
}
