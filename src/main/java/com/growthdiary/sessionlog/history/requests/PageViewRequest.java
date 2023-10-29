package com.growthdiary.sessionlog.history.requests;

public class PageViewRequest {

    private final int pageNum;

    private final int pageSize;

    public PageViewRequest(int pageNum, int pageSize) {
        this.pageNum = pageNum;
        this.pageSize = pageSize;
    }

    public int getPageNum() {
        return this.pageNum;
    }

    public int getPageSize() {
        return this.pageSize;
    }

}
