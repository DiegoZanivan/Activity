package com.task.functional.router;

import com.task.functional.handler.ActivityHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RequestPredicates.accept;

@Configuration(proxyBeanMethods = false)
public class ActivityRouter {

    @Bean
    public RouterFunction<ServerResponse> route(ActivityHandler activityHandler) {
        return RouterFunctions.route()
                .path("/v2/activity", builder -> builder
                        .GET("{id}", accept(MediaType.APPLICATION_JSON), activityHandler::findById)
                        .GET(accept(MediaType.APPLICATION_JSON), activityHandler::findAll)
                        .POST(accept(MediaType.APPLICATION_JSON), activityHandler::save)
                        .PUT(accept(MediaType.APPLICATION_JSON), activityHandler::save)
                )
                .build()
                .andRoute(GET("other"), req -> ServerResponse.ok()
                        .body(Mono.just("Other route"), String.class));
    }
}
