package com.growthdiary.sessionlog.tracker.feedback;

import jakarta.persistence.*;

/**
 * Main entity to represent user feedback on how their session went
 */
@Entity
public class Feedback {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    private Integer productivity;

    private String distraction;

    /* Default no-argument constructor required by Hibernate
     * Used during database queries
     */
    public Feedback() {}

    public Feedback(Integer productivity, String distraction) {
        this.productivity = productivity;
        this.distraction = distraction;
    }

    public void setProductivity(Integer productivityRating) {
        this.productivity = productivityRating;
    }

    public Integer getProductivity() {
        return this.productivity;
    }

    public void setDistraction(String keyDistraction) {
        this.distraction = keyDistraction;
    }

    public String getDistraction() {
        return this.distraction;
    }
}
