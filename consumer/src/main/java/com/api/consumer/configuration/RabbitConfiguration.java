package com.api.consumer.configuration;

import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.amqp.SimpleRabbitListenerContainerFactoryConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfiguration {
    @Autowired
    private ConnectionFactory connectionFactory;
    @Bean
    public MessageConverter jsonConvert(){
        return new Jackson2JsonMessageConverter();
    }
    @Bean
    public SimpleRabbitListenerContainerFactory factory(ConnectionFactory connectionFactory, SimpleRabbitListenerContainerFactoryConfigurer config){
        SimpleRabbitListenerContainerFactory facttory = new SimpleRabbitListenerContainerFactory();
        config.configure(facttory,connectionFactory);
        facttory.setMessageConverter(jsonConvert());
        return facttory;
    }
    @Bean
    public RabbitTemplate rabbitTemplate(){
        RabbitTemplate rabbit = new RabbitTemplate(connectionFactory);
        rabbit.setMessageConverter(jsonConvert());
        return rabbit;
    }
}
