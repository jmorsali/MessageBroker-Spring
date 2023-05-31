package RabbirMQ.example.RabbirMQDemo.Service;

import RabbirMQ.example.RabbirMQDemo.DTOs.MessageDto;
import RabbirMQ.example.RabbirMQDemo.Entity.BrokerMessage;

import java.util.List;


public interface MessageService {
    Boolean Push(MessageDto messageDto);

    BrokerMessage Consume();

    List<BrokerMessage> ConsumeAll();
}
