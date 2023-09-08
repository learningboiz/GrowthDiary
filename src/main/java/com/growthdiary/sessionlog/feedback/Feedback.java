package com.growthdiary.sessionlog.feedback;

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

    public Feedback() {
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
