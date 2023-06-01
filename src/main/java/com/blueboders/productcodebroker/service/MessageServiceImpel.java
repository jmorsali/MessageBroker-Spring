package com.blueboders.productcodebroker.service;


import com.blueboders.productcodebroker.configuration.RabbitMQConfig;
import com.blueboders.productcodebroker.dtos.ProductCodeDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.stereotype.Service;

@Service
public class MessageServiceImpel implements MessageService {
    private final AmqpTemplate amqpTemplate;
    public MessageServiceImpel(AmqpTemplate amqpTemplate) {
        this.amqpTemplate = amqpTemplate;
    }

    @Override
    public void Push_Q1(String message) {
        amqpTemplate.convertAndSend(RabbitMQConfig.EXCHANGE_NAME, RabbitMQConfig.ROUTING_KEY_R1, message);
    }
    @Override
    public void Push_Q2(byte[] message) {
        amqpTemplate.convertAndSend(RabbitMQConfig.EXCHANGE_NAME, RabbitMQConfig.ROUTING_KEY_R2, message);
    }

    @Override
    public void Push_Q3(ProductCodeDto productCodeDto) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            String jsonMessage = objectMapper.writeValueAsString(productCodeDto);
            amqpTemplate.convertAndSend(RabbitMQConfig.EXCHANGE_NAME, RabbitMQConfig.ROUTING_KEY_R3, jsonMessage);

        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

    }
}
