package com.growthdiary.sessionlog.skill;

import com.growthdiary.sessionlog.session.Session;
import jakarta.persistence.*;

import java.util.*;

@Entity
public class Skill {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String topic;
    private String category;

    @OneToMany(mappedBy = "skill" )
    private Set<Session> sessions = new HashSet<>();

    public Skill() {
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getTopic() {
        return this.topic;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getCategory() {
        return this.category;
    }

    public Set<Session> getSessions() {
        return sessions;
    }

    public void addSession(Session session) {
        sessions.add(session);
    }

    public void removeSession(Session session) {
        sessions.remove(session);
    }
}
