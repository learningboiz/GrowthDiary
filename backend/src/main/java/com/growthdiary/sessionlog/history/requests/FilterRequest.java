package com.growthdiary.sessionlog.history.requests;

import com.growthdiary.sessionlog.history.historyfilter.DetailsFilter;
import com.growthdiary.sessionlog.history.historyfilter.FeedbackFilter;
import com.growthdiary.sessionlog.history.historyfilter.TimeFilter;

public class FilterRequest {

    private final DetailsFilter detailsFilter;

    private final TimeFilter timeFilter;

    private final FeedbackFilter feedbackFilter;

    public FilterRequest(DetailsFilter detailsFilter, TimeFilter timeFilter, FeedbackFilter feedbackFilter) {
        this.detailsFilter = detailsFilter;
        this.timeFilter = timeFilter;
        this.feedbackFilter = feedbackFilter;
    }

    public DetailsFilter getDetailsFilter() {
        return this.detailsFilter;
    }

    public TimeFilter getTimeFilter() {
        return this.timeFilter;
    }

    public FeedbackFilter getFeedbackFilter() {
        return this.feedbackFilter;
    }

}
