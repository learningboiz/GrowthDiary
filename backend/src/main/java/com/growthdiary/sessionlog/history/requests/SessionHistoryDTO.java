package com.growthdiary.sessionlog.history.requests;

public class SessionHistoryDTO {

    private final FilterRequest filterRequest;
    private final PageViewRequest pageViewRequest;
    private final SortRequest sortRequest;

    public SessionHistoryDTO(FilterRequest filterRequest, PageViewRequest pageViewRequest, SortRequest sortRequest) {
        this.filterRequest = filterRequest;
        this.pageViewRequest = pageViewRequest;
        this.sortRequest = sortRequest;
    }

    public FilterRequest getFilterRequest() {
        return this.filterRequest;
    }

    public PageViewRequest getPageViewRequest() {
        return this.pageViewRequest;
    }

    public SortRequest getSortRequest() {
        return this.sortRequest;
    }
}
