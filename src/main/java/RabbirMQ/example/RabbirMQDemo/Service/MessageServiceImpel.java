package RabbirMQ.example.RabbirMQDemo.Service;


import RabbirMQ.example.RabbirMQDemo.DTOs.MessageDto;
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
    public Boolean Push(MessageDto messageDto) {
        try {
            amqpTemplate.convertAndSend(messageDto.getExchangeName(),messageDto.getRoutingKey(), messageDto.getMessageBody());
            return true;
        }catch(Exception exception){
            return false;
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
