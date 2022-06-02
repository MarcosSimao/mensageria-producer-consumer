package com.api.consumer.controller;

import com.api.consumer.service.RepublishService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AmqpController {
    private final RepublishService republishService;
    @GetMapping("/repost")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void repost(){
       republishService.repost();
    }
}
