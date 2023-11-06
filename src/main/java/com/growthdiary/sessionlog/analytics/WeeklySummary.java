package com.growthdiary.sessionlog.analytics;

/**
 * A projection interface to represent a summarised view of the user's weekly session data
 */
public interface WeeklySummary {

    String getTopSkill();
    Long getTotalDuration();
    Double getAvgProductivity();
}
