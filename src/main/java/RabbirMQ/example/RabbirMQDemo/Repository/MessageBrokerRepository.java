package RabbirMQ.example.RabbirMQDemo.Repository;

import RabbirMQ.example.RabbirMQDemo.Entity.BrokerMessage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MessageBrokerRepository extends JpaRepository<BrokerMessage,Long> {

    @Query("SELECT  m FROM BrokerMessage m WHERE m.id = (SELECT MAX(id) FROM BrokerMessage)")
    List<BrokerMessage> getLastMessage();
}
