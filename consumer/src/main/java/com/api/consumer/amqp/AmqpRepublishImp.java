package com.api.consumer.amqp;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Component
public class AmqpRepublishImp implements AmqpRepublish{
    @Autowired
    private RabbitTemplate rabbitTemplate;
    @Value("${spring.rabbitmq.request.routing-key.producer}")
    private String queue;
    @Value("${spring.rabbitmq.request.exchenge.producer}")
    private String exchange;
    @Value("${spring.rabbitmq.request.deadletter.producer}")
    private String deadletter;
    @Value("${spring.rabbitmq.request.parkinglot.producer}")
    private String parkinglot;

    private static final String X_RETRIES_HEADER= "x-retries";
    @Override
    @Scheduled(cron = "${spring.rabbitmq.listener.time-retry}")
    public void republish() {
       List<Message> messages = getQueueMessage();
       messages.forEach(m->{
           Map<String,Object> header=m.getMessageProperties().getHeaders();
           Integer retriesHeader=(Integer) header.get(X_RETRIES_HEADER);
           if(retriesHeader==null){
               retriesHeader=0;
           }
           if(retriesHeader<3){
             header.put(X_RETRIES_HEADER, retriesHeader+1);
             rabbitTemplate.send(exchange,queue,m);
           }else{
               rabbitTemplate.send(parkinglot,m);
           }
       });
    }

    private List<Message> getQueueMessage(){
        List<Message> messages = new ArrayList<>();
        Boolean isNull;
        Message message;
        do{
            message=rabbitTemplate.receive(deadletter);
            isNull=message!=null;
            if(Boolean.TRUE.equals(isNull)){
                messages.add(message);
            }


        }while(Boolean.TRUE.equals(isNull));
        return messages;
    }
}
