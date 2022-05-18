package com.api.consumer.service;

import com.api.consumer.model.Message;

public interface ConsumeServiceL {
    void action(Message message) throws Exception;
}
