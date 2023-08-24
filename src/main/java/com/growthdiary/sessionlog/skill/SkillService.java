package com.growthdiary.sessionlog.skill;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SkillService {

    private SkillRepository skillRepository;

    @Autowired
    public SkillService(SkillRepository skillRepository) {
        this.skillRepository = skillRepository;
    }

    public Skill createSkill(String topic, String category) {
        Skill skill = new Skill();
        skill.setTopic(topic);
        skill.setCategory(category);

        skillRepository.save(skill);

        return skill;
    }
}
