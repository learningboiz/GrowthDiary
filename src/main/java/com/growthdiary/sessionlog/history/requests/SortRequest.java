package com.growthdiary.sessionlog.history.requests;

import org.springframework.data.domain.Sort;

public class SortRequest {

    private Sort sort;

    private SortRequest() {
    }

    public static class Builder {
        private Sort.Direction direction;
        private String property;

        public Builder() {}

        private final SortRequest request = new SortRequest();

        public Builder sortBySkill() {
            if (this.property != null) {
                throw new UnsupportedOperationException("Each request can only have one sort criteria");
            } else {
                this.property = "details.skill";
                return this;
            }
        }

        public Builder sortByDate() {
            if (this.property != null) {
                throw new UnsupportedOperationException("Each request can only have one sort criteria");
            } else {
                this.property = "time.startDate";
                return this;
            }
        }

        public Builder sortByDuration() {
            if (this.property != null) {
                throw new UnsupportedOperationException("Each request can only have one sort criteria");
            } else {
                this.property = "time.duration";
                return this;
            }
        }

        public Builder sortByProductivity() {
            if (this.property != null) {
                throw new UnsupportedOperationException("Each request can only have one sort criteria");
            } else {
                this.property = "feedback.productivity";
                return this;
            }
        }

        public Builder descending() {
            if (this.direction != null) {
                throw new UnsupportedOperationException("Each request can only have one sort direction");
            } else {
                this.direction = Sort.Direction.DESC;
                return this;
            }
        }

        public Builder ascending() {
            if (this.direction != null) {
                throw new UnsupportedOperationException("Each request can only have one sort direction");
            } else {
                this.direction = Sort.Direction.ASC;
                return this;
            }
        }

        public SortRequest build() {
            if (this.property == null) {
                throw new UnsupportedOperationException("Request must specify the sort criteria");
            } else if (this.direction == null) {
                throw new UnsupportedOperationException("Request must specific sort direction");
            } else {
                request.sort = Sort.by(direction, property);
                return request;
            }
        }
    }

    public Sort getSort() {
        return sort;
    }
}
