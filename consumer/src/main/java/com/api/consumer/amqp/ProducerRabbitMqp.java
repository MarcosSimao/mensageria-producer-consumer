package com.api.consumer.amqp;

import com.api.consumer.model.Message;
import com.api.consumer.service.ConsumeServiceL;
import org.springframework.amqp.AmqpRejectAndDontRequeueException;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ProducerRabbitMqp implements AmqpConsumer<Message> {
    @Autowired
    private ConsumeServiceL consumeServiceL;
    @Override
    @RabbitListener(queues = "${spring.rabbitmq.request.routing-key.producer}")
    public void consumer(Message message) {
        try{
            consumeServiceL.action(message);
        }catch (Exception e){
            throw new AmqpRejectAndDontRequeueException(e);
        }

    }
}
