package com.growthdiary.sessionlog.tracker.details;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Main service responsible for the creation and persistence of Details object
 */
@Service
public class DetailsService {

    private final DetailsRepository detailsRepository;
    @Autowired
    public DetailsService(DetailsRepository detailsRepository) {
        this.detailsRepository = detailsRepository;
    }

    /**
     * Creates a Details object and saves it into the databse
     * @param skill Name of the skill user is working on
     * @param description Elaboration of how the skill is being learnt
     * @return Details object
     */
    public Details createDetails(String skill, String description) {
        Details details = new Details(skill, description);
        return detailsRepository.save(details);
    }
}
