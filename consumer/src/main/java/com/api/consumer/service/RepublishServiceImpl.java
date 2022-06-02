package com.api.consumer.service;

import com.api.consumer.amqp.AmqpRepublish;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RepublishServiceImpl implements RepublishService {
    @Autowired
    private AmqpRepublish amqpRepublish;
    @Override
    public void repost() {
      amqpRepublish.republish();
    }
}
