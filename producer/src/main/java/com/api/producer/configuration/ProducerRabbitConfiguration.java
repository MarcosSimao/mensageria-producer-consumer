package com.api.producer.configuration;

import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ProducerRabbitConfiguration {
    @Value("${spring.rabbitmq.request.routing-key.producer}")
    private String queue;
    @Value("${spring.rabbitmq.request.exchenge.producer}")
    private String exchange;
    @Value("${spring.rabbitmq.request.deadletter.producer}")
    private String deadletter;
    @Value("${spring.rabbitmq.request.parkinglot.producer}")
    private String parkinglot;

    @Bean
    DirectExchange exchange(){
        return new DirectExchange(exchange);
    }
    @Bean
    Queue deadletter(){

       return QueueBuilder.durable(deadletter)
               .deadLetterExchange(exchange)
               .deadLetterRoutingKey(queue)
               .build();
    }

    @Bean
    Queue queue(){
        return QueueBuilder.durable(queue)
                .deadLetterExchange(exchange)
                .deadLetterRoutingKey(deadletter)
                .build();

//        Map<String,Object> args = new HashMap<>();
//        args.put("x-dead-letter-exchange",exchange);
//        args.put("x-dead-letter-routing-key",deadletter);
//        return new Queue(queue, true,false,false,args);
    }
    @Bean
    Queue parkingLot(){
       return new Queue(parkinglot);
    }
    @Bean
    Binding bindingQueue(){
        return BindingBuilder.bind(queue())
                .to(exchange()).with(queue);
    }
    @Bean
    public Binding bindingDeadLetter(){
        return BindingBuilder.bind(deadletter())
                .to(exchange()).with(deadletter);
    }

    @Bean
    public Binding bindingParkingLot(){
        return BindingBuilder.bind(parkingLot())
                .to(exchange())
                .with(parkinglot);
    }
}
