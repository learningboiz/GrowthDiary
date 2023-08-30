package com.growthdiary.sessionlog.skill;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SkillController {

    private SkillService skillService;

    @Autowired
    public SkillController(SkillService skillService) {
        this.skillService = skillService;
    }

    @PostMapping("/skill")
    public ResponseEntity<Skill> skill(@RequestParam String topic,
                                       @RequestParam String category)
    {
        Skill skill = skillService.createSkill(topic, category);
        return new ResponseEntity<>(skill, HttpStatus.CREATED);
    }
}
