package com.growthdiary.sessionlog.history;

import com.growthdiary.sessionlog.session.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class HistoryService {

    private final HistoryRepository historyRepository;


    /** Injects sessionRepository bean into the Spring context
     *
     * This repository has all necessary data regarding user's session history
     */
    @Autowired
    public HistoryService(HistoryRepository historyRepository) {
        this.historyRepository = historyRepository;
    }


    /** Creates a Sort object that specifies property to be sorted and order of sorting
     *
     * @param direction The order to sort results in either ASC or DESC
     * @param property The property to be sorted
     * @return Sort object with specified sort direction and property
     */
    public Sort createSort(SortDirection direction, String property) {
        if (direction == SortDirection.ASC) {
            return Sort.by(Sort.Direction.ASC, property);
        } else {
            return Sort.by(Sort.Direction.DESC, property);
        }
    }

    public List<Session> getAllSessions() {
        Specification<Session> spec = FilterSpecifications.joinTables();
        return historyRepository.findAll(spec);
    }

    /**
     * Creates a list of sessions with skills matching any of the provided skill names
     * @param givenSkills list of skills to be matched with existing sessions in the database
     * @return a list of filtered sessions
     */
    public List<Session> filterBySkill(List<String> givenSkills) {
        Specification<Session> skillSpec = FilterSpecifications.skillIs(givenSkills);
        return historyRepository.findAll(skillSpec);
    }

    /**
     * Creates a list of sessions with descriptions matching the given keywords
     * @param keywords Keywords that will be matched against descriptions in the database
     * @return a list of filtered sessions
     */
    public List<Session> filterByDescription(String keywords) {
        Specification<Session> descriptionSpec = FilterSpecifications.descriptionLike(keywords);
        return historyRepository.findAll(descriptionSpec);
    }

    /**
     * Creates a list of sessions with durations within the specified ranges
     * @param operator ENUM that specifies the mode of comparison
     * @param givenDuration list of specified durations to evaluate
     * @return a list of filtered sessions
     */
    public List<Session> filterByDuration(FilterOperators operator, List<Long> givenDuration) {
        Specification<Session> durationSpecs;

        if (operator == FilterOperators.BETWEEN) {
            durationSpecs = FilterSpecifications.durationBetween(givenDuration.get(0), givenDuration.get(1));
        } else {
            durationSpecs = FilterSpecifications.durationEqualsUnderAbove(operator, givenDuration.get(0));
        }

        return historyRepository.findAll(durationSpecs);
    }

    /**
     * Creates a list of sessions with dates within the specified ranges
     * @param operator ENUM that specifies the mode of comparison
     * @param givenDates list of specified dates to evaluate
     * @return a list of filtered sessions
     */
    public List<Session> filterByDate(FilterOperators operator, List<LocalDate> givenDates) {
        Specification<Session> dateSpecs;

        if (operator == FilterOperators.BETWEEN) {
            dateSpecs = FilterSpecifications.dateBetween(givenDates.get(0), givenDates.get(1));
        } else {
            dateSpecs = FilterSpecifications.dateEqualsBeforeAfter(operator, givenDates.get(0));
        }

        return historyRepository.findAll(dateSpecs);
    }

    /**
     * Creates a list of sessions with productivity ratings within the specified ranges
     * @param operator ENUM that specifies the mode of comparison
     * @param givenProductivity list of specified productivity ratings to evaluate
     * @return a list of filtered sessions
     */
    public List<Session> filterByProductivity(FilterOperators operator, List<Integer> givenProductivity) {
        Specification<Session> productivitySpecs;

        if (operator == FilterOperators.BETWEEN) {
            productivitySpecs = FilterSpecifications.productivityBetween(givenProductivity.get(0), givenProductivity.get(1));
        } else {
            productivitySpecs = FilterSpecifications.productivityEqualsUnderAbove(operator, givenProductivity.get(0));
        }
        return historyRepository.findAll(productivitySpecs);
    }

    /**
     * Creates a list of sessions with distractions matching any of the provided distractions
     * @param givenDistractions list of distractions to be matched with existing sessions in the database
     * @return a list of filtered sessions
     */
    public List<Session> filterByDistraction(List<String> givenDistractions) {
        Specification<Session> distractionSpec = FilterSpecifications.distractionIs(givenDistractions);
        return historyRepository.findAll(distractionSpec);
    }
}
