package com.example.flux.controller;

import com.example.flux.dom.Message;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
@RequestMapping("/controller")
public class MainController {
    @GetMapping
    Flux<Message> list(
            @RequestParam(defaultValue = "0") Long start
            , @RequestParam(defaultValue ="5") Long count
    ){
        return Flux
                .just(
                        "First",
                        "Second",
                        "One more"
                )
                .skip(start)
                .take(count)
                .map(Message::new);
    }
}
