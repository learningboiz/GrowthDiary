package com.growthdiary.sessionlog.skill;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SkillService {

    private SkillRepository skillRepository;

    /** Inject the skillRepository bean into the Spring context since only one instance is required
     *
     * The bean instance will be solely responsible for interactions with the database
     * @param skillRepository bean instance of the skillRepository injected by Spring
     */
    @Autowired
    public SkillService(SkillRepository skillRepository) {
        this.skillRepository = skillRepository;
    }

    /** Creates a Skill object and saves the data into the database
     *
     * Separated from Session model in case properties require refactoring
     * @param topic the specific focus on the session
     * @param category the overarching category that the topic is part of
     * @return a Skill object to be included when creating a Session Object
     */
    public Skill createSkill(String topic, String category) {
        Skill skill = new Skill();
        skill.setTopic(topic);
        skill.setCategory(category);

        skillRepository.save(skill);

        return skill;
    }
}
