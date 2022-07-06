package com.task.functional.handler;

import com.task.converter.ActivityConverter;
import com.task.dto.ActivityDto;
import com.task.data.service.ActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import static org.springframework.web.reactive.function.server.ServerResponse.ok;

@Component
public class ActivityHandler {

    @Autowired
    private ActivityService service;

    private ActivityConverter converter;

    public ActivityHandler() {
        converter = new ActivityConverter();
    }

    public Mono<ServerResponse> findAll(ServerRequest request) {
        return ok().contentType(MediaType.APPLICATION_JSON)
                .body(service.findAll()
                        .flatMap(x -> converter.convertToDto(x)), ActivityDto.class);
    }

    public Mono<ServerResponse> findById(ServerRequest request) {
        return ok().contentType(MediaType.APPLICATION_JSON)
                .body(service.findById(request.pathVariable("id"))
                        .flatMap(x -> converter.convertToDto(x)), ActivityDto.class);
    }

    public Mono<ServerResponse> save(ServerRequest request) {
        return ok().contentType(MediaType.APPLICATION_JSON)
                .body(request.bodyToMono(ActivityDto.class)
                        .flatMap(dto -> converter.convertToDocument(service, dto))
                        .flatMap(doc -> service.save(doc))
                        .doOnNext(doc -> converter.convertToDto(doc)), ActivityDto.class);
    }
}
