package com.growthdiary.sessionlog.history.historyfilter;

import com.growthdiary.sessionlog.history.requests.FilterRequest;

/**
 * The {@code FilterOperations} enum represents the valid operations that can be used when filtering session attributes.
 * These operations define how values can be compared.
 * @see FilterRequest
 */
public enum FilterOperations {
    
    EQUALS,
    LESS_THAN,
    GREATER_THAN,
    BETWEEN
}
