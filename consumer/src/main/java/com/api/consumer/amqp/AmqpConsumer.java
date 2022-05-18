package com.api.consumer.amqp;

public interface AmqpConsumer<T>{

     void consumer(T t);
}
