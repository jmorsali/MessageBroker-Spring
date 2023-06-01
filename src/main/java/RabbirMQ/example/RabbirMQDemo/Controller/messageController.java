package RabbirMQ.example.RabbirMQDemo.Controller;

import RabbirMQ.example.RabbirMQDemo.DTOs.MessageDto;
import RabbirMQ.example.RabbirMQDemo.Entity.BrokerMessage;
import RabbirMQ.example.RabbirMQDemo.Service.CSVFileService;
import RabbirMQ.example.RabbirMQDemo.Service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("api/v1/message")
public class messageController {

    private final MessageService messageService;
    private final CSVFileService csvFileService;
    @Autowired
    public messageController(MessageService messageService, CSVFileService csvFileService) {

        this.messageService = messageService;
        this.csvFileService = csvFileService;
    }

    @PutMapping("push")
    public ResponseEntity<String> Push(@RequestBody String message) {
        try {
        messageService.Push(message);
            return new ResponseEntity<>("Message Pushed successfully", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("UnHandled error during message pushing ::>" + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);

        }
    }
    @PutMapping("pushfile")
    public ResponseEntity<String> PushFile(@RequestPart("file") MultipartFile file) throws IOException {
        if (!file.isEmpty()) {

            try {
                byte[] bytes = file.getBytes();
                String csvData = new String(bytes);
                messageService.Push(csvData);
                return new ResponseEntity<>("File uploaded successfully", HttpStatus.OK);
            } catch (Exception e) {
                return new ResponseEntity<>("UnHandled error during file process ::>" + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);

            }
        } else {
            return new ResponseEntity<>("No file uploaded", HttpStatus.BAD_REQUEST);
        }
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
