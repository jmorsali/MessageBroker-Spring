package RabbirMQ.example.RabbirMQDemo.Component;

import RabbirMQ.example.RabbirMQDemo.DTOs.ProductCodeDto;
import RabbirMQ.example.RabbirMQDemo.Service.CSVFileService;
import RabbirMQ.example.RabbirMQDemo.Service.MessageService;
import RabbirMQ.example.RabbirMQDemo.Service.ProductCodeService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
@Component
public class QueueListener_Q2 {

    private final CSVFileService csvFileService;
    private final MessageService messageService;
    @Autowired
    public QueueListener_Q2(CSVFileService csvFileService, MessageService messageService) {
        this.csvFileService = csvFileService;
        this.messageService = messageService;
    }
    @RabbitListener(queues = "Q2")
    public void listenToQueue2(String csvFileContent) {
        System.out.println("Received from queue 2: " + csvFileContent);
        var productDtoCodes = csvFileService.processFile(csvFileContent);
        for (var productDtoCode : productDtoCodes) {
            messageService.Push_Q3(productDtoCode);
        }
    }


}