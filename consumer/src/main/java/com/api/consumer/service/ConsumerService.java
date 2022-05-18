package com.api.consumer.service;

import com.api.consumer.model.Message;
import org.springframework.stereotype.Service;

@Service
public class ConsumerService implements ConsumeServiceL{

    @Override
    public void action(Message message) throws Exception {

        System.out.println(message.getText());
    }
}
