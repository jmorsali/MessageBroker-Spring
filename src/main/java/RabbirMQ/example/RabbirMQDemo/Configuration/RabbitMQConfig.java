package RabbirMQ.example.RabbirMQDemo.Configuration;

import RabbirMQ.example.RabbirMQDemo.Component.MessageConsumer_Q1;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    public static final String QUEUE_NAME = "wallet-queue";
    public static final String EXCHANGE_NAME = "wallet-exchange";
    public static final String ROUTING_KEY = "wallet.*.*";

    @Bean
    public Queue queue() {
        return new Queue(QUEUE_NAME, true);
    }

    @Bean
    public Binding binding(Queue queue) {
        return BindingBuilder.bind(queue).to(exchange()).with(ROUTING_KEY).noargs();
    }

    @Bean
    public Exchange exchange() {
        return new TopicExchange(EXCHANGE_NAME);
    }

    @Autowired
    private MessageConsumer_Q1 messageConsumer;

    @Autowired
    private ConnectionFactory connectionFactory;

    @Bean
    public SimpleMessageListenerContainer messageListenerContainer() {
        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);
        container.setQueues(queue());
        container.setMessageListener(messageConsumer);
        return container;
    }
}
