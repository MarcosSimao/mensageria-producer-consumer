package com.api.consumer.service;

import com.api.consumer.model.MessageModel;

public interface ConsumeServiceL {
    void action(MessageModel message) throws Exception;
}
