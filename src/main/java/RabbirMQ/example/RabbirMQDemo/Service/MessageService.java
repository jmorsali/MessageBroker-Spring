package RabbirMQ.example.RabbirMQDemo.Service;

import RabbirMQ.example.RabbirMQDemo.DTOs.MessageDto;
import RabbirMQ.example.RabbirMQDemo.DTOs.ProductCodeDto;
import RabbirMQ.example.RabbirMQDemo.Entity.BrokerMessage;

import java.util.List;


public interface MessageService {

    void Push_Q1(String message);

    void Push_Q2(byte[] message);

    void Push_Q3(ProductCodeDto productCodeDto);

    BrokerMessage Consume();

    List<BrokerMessage> ConsumeAll();


}
