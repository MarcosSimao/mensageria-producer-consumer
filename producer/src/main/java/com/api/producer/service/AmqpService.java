package com.api.producer.service;

import com.api.producer.model.Message;

public interface AmqpService {
    void sendToConsumer(Message message);
}
