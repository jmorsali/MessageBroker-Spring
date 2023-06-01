package RabbirMQ.example.RabbirMQDemo.Service;


import RabbirMQ.example.RabbirMQDemo.Configuration.RabbitMQConfig;
import RabbirMQ.example.RabbirMQDemo.Entity.BrokerMessage;
import RabbirMQ.example.RabbirMQDemo.Repository.MessageBrokerRepository;
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
    public void Push(String message) {
        amqpTemplate.convertAndSend(RabbitMQConfig.EXCHANGE_NAME, RabbitMQConfig.ROUTING_KEY, message);
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
