package com.growthdiary.sessionlog.analytics;

import java.util.List;

/**
 * A projection interface to represent a summarised view of the user's weekly session data
 */
public interface SessionStats {

    // TODO find a more maintainable way to name these methods + the queries
    // a change in domain names require changes to these which can be overlooked leading to bugs

    String getTopTopic();
    String getTopObstacle();
    Long getTotalDuration();
    Long getAvgDuration();
    Double getAvgProductivity();
}
