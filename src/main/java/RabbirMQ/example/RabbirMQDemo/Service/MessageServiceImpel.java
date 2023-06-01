package RabbirMQ.example.RabbirMQDemo.Service;


import RabbirMQ.example.RabbirMQDemo.Configuration.RabbitMQConfig;
import RabbirMQ.example.RabbirMQDemo.DTOs.ProductCodeDto;
import RabbirMQ.example.RabbirMQDemo.Entity.BrokerMessage;
import RabbirMQ.example.RabbirMQDemo.Repository.MessageBrokerRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MessageServiceImpel implements MessageService {
    private final AmqpTemplate amqpTemplate;
    private final MessageBrokerRepository  messageBrokerRepository;
    public MessageServiceImpel(AmqpTemplate amqpTemplate, MessageBrokerRepository messageBrokerRepository) {
        this.amqpTemplate = amqpTemplate;
        this.messageBrokerRepository = messageBrokerRepository;
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

    @Override
    public BrokerMessage Consume() {
       return messageBrokerRepository.getLastMessage().stream().findFirst().orElse(null);
    }

    @Override
    public List<BrokerMessage> ConsumeAll() {
        return messageBrokerRepository.findAll().stream().toList();
    }
}
