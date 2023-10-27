package com.growthdiary.sessionlog.history.historyfilter;

import java.util.List;

public class DetailsFilter {
    private List<String> skills;

    private String description;

    private DetailsFilter() {
    }

    public List<String> getSkills() {
        return this.skills;
    }

    public String getDescription() {
        return this.description;
    }

    public static class BuildFilter {
        private final DetailsFilter filter = new DetailsFilter();

        public BuildFilter filterSkill(List<String> skills) {
            filter.skills = skills;
            return this;
        }

        public BuildFilter filterDescription(String description) {
            filter.description = description;
            return this;
        }

        public DetailsFilter build() {
            return filter;
        }
    }
 }
