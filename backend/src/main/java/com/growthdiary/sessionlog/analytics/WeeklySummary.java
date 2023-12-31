package com.growthdiary.sessionlog.analytics;

/**
 * A projection interface to represent a summarised view of the user's weekly session data
 */
public interface WeeklySummary {

    // TODO find a more maintainable way to name these methods + the queries
    // a change in domain names require changes to these which can be overlooked leading to bugs

    String getTopTopic();
    Long getTotalDuration();
    Double getAvgProductivity();
}
