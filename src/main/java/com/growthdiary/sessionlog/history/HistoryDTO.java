package com.growthdiary.sessionlog.history;

import com.growthdiary.sessionlog.history.requests.FilterRequest;
import org.springframework.data.domain.Sort;

import java.util.List;

public class HistoryDTO {

    private final List<FilterRequest> filterRequest;

    private final int pageNum;

    private final int pageSize;

    private final Sort sort;

    public HistoryDTO(List<FilterRequest> filterRequest, int pageNum, int pageSize, Sort sort) {
        this.filterRequest = filterRequest;
        this.pageNum = pageNum;
        this.pageSize = pageSize;
        this.sort = sort;
    }

    public List<FilterRequest> getFilterRequest() {
        return this.filterRequest;
    }

    public int getPageNum() {
        return this.pageNum;
    }

    public int getPageSize() {
        return this.pageSize;
    }

    public Sort getSort() {
        return this.sort;
    }
}
