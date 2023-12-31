package com.growthdiary.sessionlog.analytics;

/**
 * A projection interface to represent correlations between the specified attribute and productivity of user
 */
public interface ProductivityChart {

    String getCategory();
    Double getAverageProductivity();
}
