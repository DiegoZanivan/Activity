package com.task.data.service;

import com.task.data.document.Activity;
import com.task.global.exception.EntryNotFoundException;
import com.task.global.exception.InvalidEntryException;
import com.task.data.repository.ActivityRepository;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class ActivityService {

    @Autowired
    private ActivityRepository repository;

    public Mono<Activity> save(Activity activity) {
        if (Strings.isBlank(activity.getDescription())) {
            return Mono.error(new InvalidEntryException());
        }
        return repository.save(activity);
    }

    public Mono<Activity> findById(String id) {
        return repository.findById(id)
                .switchIfEmpty(Mono.error(new EntryNotFoundException(id)));
    }

    public Flux<Activity> findAll() {
        return repository.findAll();
    }
}
