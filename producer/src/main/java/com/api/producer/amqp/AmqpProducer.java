package com.api.producer.amqp;

public interface AmqpProducer<T>{
    void producer(T t);
}
