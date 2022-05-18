package com.api.producer.service;

import com.api.producer.amqp.AmqpProducer;
import com.api.producer.model.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RabbitService implements AmqpService {
    @Autowired
    private AmqpProducer<Message> amqp;
    @Override
    public void sendToConsumer(Message message) {
     amqp.producer(message);
    }
}
