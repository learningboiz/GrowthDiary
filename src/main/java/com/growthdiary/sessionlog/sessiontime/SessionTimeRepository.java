package com.growthdiary.sessionlog.sessiontime;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SessionTimeRepository extends CrudRepository<SessionTime, Long> {
}
