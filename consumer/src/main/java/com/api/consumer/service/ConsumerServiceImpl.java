package com.api.consumer.service;

import com.api.consumer.model.MessageModel;
import org.springframework.amqp.AmqpRejectAndDontRequeueException;
import org.springframework.stereotype.Service;

@Service
public class ConsumerServiceImpl implements ConsumeServiceL{

    @Override
    public void action(MessageModel message) {
       if("marcos".equalsIgnoreCase(message.getText())){
           throw new AmqpRejectAndDontRequeueException("mande uma nova mensagem sem o nome marcos");
       }
        System.out.println(message.getText());
    }
}
