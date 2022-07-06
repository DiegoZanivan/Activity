package com.task.data.repository;

import com.task.data.document.Activity;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface ActivityRepository extends ReactiveMongoRepository<Activity, String> {
}
