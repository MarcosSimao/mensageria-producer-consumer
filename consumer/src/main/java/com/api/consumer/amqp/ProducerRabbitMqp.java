package com.api.consumer.amqp;

import com.api.consumer.model.MessageModel;
import com.api.consumer.service.ConsumeServiceL;
import org.springframework.amqp.AmqpRejectAndDontRequeueException;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ProducerRabbitMqp implements AmqpConsumer<MessageModel> {
    @Autowired
    private ConsumeServiceL consumeServiceL;
    @Override
    @RabbitListener(queues = "${spring.rabbitmq.request.routing-key.producer}")
    public void consumer(MessageModel message) {
        try{
            consumeServiceL.action(message);
        }catch (Exception e){
            throw new AmqpRejectAndDontRequeueException(e);
        }

    }
}
