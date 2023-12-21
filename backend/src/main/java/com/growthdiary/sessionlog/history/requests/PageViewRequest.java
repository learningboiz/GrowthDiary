package com.growthdiary.sessionlog.history.requests;

public class PageViewRequest {

    private final Integer pageIndex;

    private final Integer pageSize;

    public PageViewRequest(Integer pageIndex, Integer pageSize) {
        this.pageIndex = pageIndex;
        this.pageSize = pageSize;
    }

    public Integer getPageIndex() {
        return this.pageIndex;
    }

    public Integer getPageSize() {
        return this.pageSize;
    }

}
