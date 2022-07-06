package com.task.annotated.controller;

import com.task.data.document.Activity;
import com.task.data.service.ActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("v1/activity")
public class ActivityV1Controller {

    @Autowired
    private ActivityService service;

    @PostMapping
    public Mono<Activity> save(@RequestBody Activity activity) {
        return service.save(activity);
    }

    @GetMapping("{id}")
    public Mono<Activity> getById(@PathVariable("id") String id) {
        return service.findById(id);
    }

    @GetMapping
    public Flux<Activity> getAll() {
        return service.findAll();
    }
}
