package com.growthdiary.sessionlog.history;

public class HistoryViewDTO {

    private final int pageNum;

    private final int pageSize;

    public HistoryViewDTO(int pageNum, int pageSize) {
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
