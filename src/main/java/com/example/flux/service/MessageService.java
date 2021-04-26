package com.example.flux.service;

import com.example.flux.dom.Message;
import com.example.flux.repo.MessageRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class MessageService {
    private final MessageRepo messageRepo;

    @Autowired     //deprecated
    public MessageService(MessageRepo messageRepo) {
        this.messageRepo = messageRepo;
    }

    public Flux<Message> list(){
        return messageRepo.findAll();
    }

    public Mono<Message> addOneMessage(Message message){
        return messageRepo.save(message);
    }
}
