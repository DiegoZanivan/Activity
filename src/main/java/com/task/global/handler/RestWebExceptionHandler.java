package com.task.global.handler;

import com.task.global.exception.EntryNotFoundException;
import com.task.global.exception.InvalidEntryException;
import org.apache.logging.log4j.util.Strings;
import org.springframework.core.annotation.Order;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebExceptionHandler;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.nio.charset.StandardCharsets;

@Component
@Order(-2)
public class RestWebExceptionHandler implements WebExceptionHandler {

    @Override
    public Mono<Void> handle(ServerWebExchange exchange, Throwable ex) {
        if (ex instanceof EntryNotFoundException) {
            exchange.getResponse().setStatusCode(HttpStatus.NOT_FOUND);
        }

        if (ex instanceof InvalidEntryException) {
            exchange.getResponse().setStatusCode(HttpStatus.BAD_REQUEST);
        }

        if (Strings.isNotBlank(ex.getMessage())) {
            byte[] bytes = ex.getMessage().getBytes(StandardCharsets.UTF_8);
            DataBuffer buffer = exchange.getResponse().bufferFactory().wrap(bytes);
            return exchange.getResponse().writeWith(Flux.just(buffer));
        }

        return Mono.error(ex);
    }

}
