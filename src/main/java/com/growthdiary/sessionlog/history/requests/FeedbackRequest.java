package com.growthdiary.sessionlog.history.requests;

import com.growthdiary.sessionlog.history.filters.FilterOperations;

import java.util.List;

public class FeedbackRequest {

    List<String> distractions;

    FilterOperations prodOp;
    Integer minProd;

    Integer maxProd;

    public FeedbackRequest() {}

    public void setDistractions(List<String> distractions) {
        this.distractions = distractions;
    }

    public List<String> getDistractions() {
        return this.distractions;
    }

    public void setProdOp(FilterOperations prodOp) {
        this.prodOp = prodOp;
    }

    public FilterOperations getProdOp() {
        return this.prodOp;
    }

    public void setMinProd(Integer minProd) {
        this.minProd = minProd;
    }

    public Integer getMinProd() {
        return this.minProd;
    }

    public void setMaxProd(Integer maxProd) {
        this.maxProd = maxProd;
    }

    public Integer getMaxProd() {
        return this.maxProd;
    }
}
