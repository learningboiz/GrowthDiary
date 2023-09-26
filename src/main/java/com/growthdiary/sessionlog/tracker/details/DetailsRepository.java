package com.growthdiary.sessionlog.tracker.details;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DetailsRepository extends CrudRepository<Details, Long> {
}
