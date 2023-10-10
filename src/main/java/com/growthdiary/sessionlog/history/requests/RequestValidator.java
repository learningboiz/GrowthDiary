package com.growthdiary.sessionlog.history.requests;

import com.growthdiary.sessionlog.history.filters.FilterOperations;

import java.time.LocalDate;

/**
 * The {@code RequestValidator} class provides methods to validate the arguments passed into filter requests
 * <p>
 * This class supports validation for dates, duration and productivity
 * <p>
 * Methods return {@code IllegalArgumentException} when arguments are invalid
 */
public class RequestValidator {

    private static boolean withinRange(int value) {
        int min = 1;
        int max = 5;
        return value >= min && value <= max;
    }

    /**
     * Validates the date filter parameters
     *
     * @param minDate Minimum date value
     * @param maxDate Maximum date value (required for BETWEEN operations)
     * @param dateOp The filter operation to apply (EQUALS, GREATER_THAN, LESS_THAN, BETWEEN)
     * @throws IllegalArgumentException If the parameters do not meet validation criteria
     */
    public static void validateDate(LocalDate minDate, LocalDate maxDate, FilterOperations dateOp) {
        if (dateOp == FilterOperations.BETWEEN) {
            if (maxDate == null) {
                throw new IllegalArgumentException("Max date value be be provided for BETWEEN operations");
            }
            if (minDate.isAfter(maxDate)) {
                throw new IllegalArgumentException("Min date must come before max date");
            }
        }
    }

    /**
     * Validates the duration filter parameters
     *
     * @param minDuration Minimum duration value (must be non-negative)
     * @param maxDuration Maximum duration value (required for BETWEEN operations)
     * @param durationOp The filter operation to apply (EQUALS, GREATER_THAN, LESS_THAN, BETWEEN)
     * @throws IllegalArgumentException If the parameters do not meet validation criteria
     */
    public static void validateDuration(Long minDuration, Long maxDuration, FilterOperations durationOp) {
        if (minDuration < 0 || (maxDuration !=null && maxDuration < 0)) {
            throw new IllegalArgumentException("Durations must be a non-negative value");
        }

        if (durationOp == FilterOperations.BETWEEN) {
            if (maxDuration == null) {
                throw new IllegalArgumentException("Max duration value be be provided for BETWEEN operations");
            }
            if (minDuration > maxDuration) {
                throw new IllegalArgumentException("Min duration value must be less than max duration");
            }
        }
    }

    /**
     * Validates the productivity filter parameters
     *
     * @param minProd Minimum duration value (must be between 1 and 5)
     * @param maxProd Maximum duration value (required for BETWEEN operations)
     * @param prodOp The filter operation to apply (EQUALS, GREATER_THAN, LESS_THAN, BETWEEN)
     * @throws IllegalArgumentException If the parameters do not meet validation criteria
     */
    public static void validateProductivity(Integer minProd, Integer maxProd, FilterOperations prodOp) {
        if (!withinRange(minProd) || (maxProd != null && !withinRange(maxProd))) {
            throw new IllegalArgumentException("Productivity value must be between the range of 1 to 5 inclusive");
        }

        if (prodOp == FilterOperations.BETWEEN) {
            if (maxProd == null) {
                throw new IllegalArgumentException("Max productivity value must be provided for BETWEEN operations");
            }
            if (minProd > maxProd) {
                throw new IllegalArgumentException("Min productivity value must be less than max productivity");
            }
        }
    }


}
