package RabbirMQ.example.RabbirMQDemo.Controller;

import RabbirMQ.example.RabbirMQDemo.DTOs.MessageDto;
import RabbirMQ.example.RabbirMQDemo.Entity.BrokerMessage;
import RabbirMQ.example.RabbirMQDemo.Service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/message")
public class messageController {

    private final MessageService messageService;

    @Autowired
    public messageController(MessageService messageService) {

        this.messageService = messageService;
    }

    @PutMapping("push")
    public ResponseEntity<Boolean> Push(@RequestBody MessageDto message) {
        Boolean result = messageService.Push(message);
        return ResponseEntity.ok(result);
    }

    @GetMapping("consume/last")
    public ResponseEntity<BrokerMessage> ConsumeLast() {
        var result = messageService.Consume();
        return ResponseEntity.ok(result);
    }

    @GetMapping("consume/all")
    public ResponseEntity<List<BrokerMessage>> ConsumeAll() {
        var results = messageService.ConsumeAll();
        return ResponseEntity.ok(results);
    }
}
