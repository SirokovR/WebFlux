package com.example.flux.controller;

import com.example.flux.dom.Message;
import com.example.flux.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/controller")
public class MainController {
    private final MessageService messageService;

    @Autowired //deprecated
    public MainController(MessageService messageService) {
        this.messageService = messageService;
    }

    @GetMapping
    Flux<Message> list(
            @RequestParam(defaultValue = "0") Long start
            , @RequestParam(defaultValue ="5") Long count
    ){
        return messageService.list();
    }

    @PostMapping
    Mono<Message> add(Message message){
        return messageService.addOneMessage(message);
    }
}
