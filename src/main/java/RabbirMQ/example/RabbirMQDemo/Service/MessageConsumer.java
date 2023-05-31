package RabbirMQ.example.RabbirMQDemo.Service;

import RabbirMQ.example.RabbirMQDemo.Entity.BrokerMessage;
import RabbirMQ.example.RabbirMQDemo.Repository.MessageBrokerRepository;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MessageConsumer implements MessageListener {

    private final MessageBrokerRepository messageBrokerRepository;

    @Autowired
    public MessageConsumer(MessageBrokerRepository messageBrokerRepository) {
        this.messageBrokerRepository = messageBrokerRepository;
    }

    @Override
    public void onMessage(Message message) {
        String receivedMessage = new String(message.getBody());
        var messageEntity = new BrokerMessage();
        messageEntity.setMessage(receivedMessage);
        messageBrokerRepository.save(messageEntity);

        System.out.println("Received message: " + receivedMessage);
    }
}
