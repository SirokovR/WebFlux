package com.example.flux.handler;

import com.example.flux.dom.Message;
import org.springframework.http.MediaType;
import org.springframework.http.ReactiveHttpOutputMessage;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserter;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Map;

@Component
public class GreetingHandler {

  public Mono<ServerResponse> hello(ServerRequest request) {

    Long start = request.queryParam("start")
            .map(Long::valueOf)
            .orElse(0L);

    Long count = request.queryParam("count")
            .map(Long::valueOf)
            .orElse(3L);

    Flux<Message> map = Flux
            .just(
                    "First",
                    "Second",
                    "One more"
            )
            .skip(start)
            .take(count)
            .map(Message::new);

    return ServerResponse
            .ok()
            .contentType(MediaType.APPLICATION_JSON)
      .body(map, Message.class);
  }

  public Mono<ServerResponse> index(ServerRequest serverRequest){
    String user = serverRequest.queryParam("user")
            .orElse("Nobody");
    return ServerResponse
            .ok()
            //.contentType(MediaType.TEXT_PLAIN)  определяем возвращая html ом
            //.body(BodyInserters.fromValue("Main Paige"));
            .render("index", Map.of("user",user));

  }
}
