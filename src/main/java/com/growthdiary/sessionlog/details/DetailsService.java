package com.growthdiary.sessionlog.details;

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
     * Takes in user input to create a Details object for the session
     * @param skill Name of the skill user is working on
     * @param description Elaboration of how the skill is being learnt
     * @return Details object
     */
    public Details createDetails(String skill, String description) {
        return new Details(skill, description);
    }

    /**
     * Takes in a Details object to persist it into the database
     * @param details Details object
     */
    public void saveDetails(Details details) {
        detailsRepository.save(details);
    }
}
