package com.api.producer.controller;

import com.api.producer.model.Message;
import com.api.producer.service.AmqpService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import org.springframework.web.bind.annotation.RestController;

@RestController
public class AmqpCoontroller {
    @Autowired
    private AmqpService amqpService;

    @PostMapping("/send")
    public ResponseEntity<Void> sendToConsumer(@RequestBody Message message){
        System.out.println(message);
        amqpService.sendToConsumer(message);
        return ResponseEntity.accepted().build();
    }
}
