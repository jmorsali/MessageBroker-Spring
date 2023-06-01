package com.blueboders.productcodebroker.configuration;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    public static final String QUEUE_NAME_Q1 = "Q1";
    public static final String QUEUE_NAME_Q2 = "Q2";
    public static final String QUEUE_NAME_Q3 = "Q3";
    public static final String EXCHANGE_NAME = "main-exchange";
    public static final String ROUTING_KEY_R1 = "R1.*.*";
    public static final String ROUTING_KEY_R2 = "R2.*.*";
    public static final String ROUTING_KEY_R3 = "R3.*.*";



    private final ConnectionFactory connectionFactory;

    public RabbitMQConfig(ConnectionFactory connectionFactory) {
        this.connectionFactory = connectionFactory;
    }

    @Bean
    public RabbitAdmin rabbitAdmin() {
        return new RabbitAdmin(connectionFactory);
    }
    @Bean
    public Queue queue1() {
        return new Queue(QUEUE_NAME_Q1, true);
    }
    @Bean
    public Queue queue2() {
        return new Queue(QUEUE_NAME_Q2, true);
    }
    @Bean
    public Queue queue3() {
        return new Queue(QUEUE_NAME_Q3, true);
    }

    @Bean
    public Binding bindingQ1(Queue queue1) {
        return BindingBuilder.bind(queue1).to(exchange()).with(ROUTING_KEY_R1).noargs();
    }
    @Bean
    public Binding bindingQ2(Queue queue2) {
        return BindingBuilder.bind(queue2).to(exchange()).with(ROUTING_KEY_R2).noargs();
    }
    @Bean
    public Binding bindingQ3(Queue queue3) {
        return BindingBuilder.bind(queue3).to(exchange()).with(ROUTING_KEY_R3).noargs();
    }

    @Bean
    public Exchange exchange() {
        return new TopicExchange(EXCHANGE_NAME);
    }

}
