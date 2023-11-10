package com.growthdiary.sessionlog.history.historyfilter;

import java.util.List;

/**
 * A filter used to create a customised view of past sessions that match the specified details-related criteria
 *
 * @see FeedbackFilter
 * @see TimeFilter
 */
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

    /**
     * Builder class used to create a DetailsFilter object.
     * Allows creation of filter that finds sessions which match the given skills, description or both.
     */
    public static class BuildFilter {
        private final DetailsFilter filter = new DetailsFilter();

        public BuildFilter findSkillIn(List<String> skills) {
            filter.skills = skills;
            return this;
        }

        public BuildFilter findDescriptionLike(String description) {
            filter.description = description;
            return this;
        }

        public DetailsFilter build() {
            return filter;
        }
    }
 }
