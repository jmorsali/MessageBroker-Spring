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
public class QueueListener {

    private final ProductCodeService productCodeService;
    private final CSVFileService csvFileService;
    private final MessageService messageService;
    @Autowired
    public QueueListener(ProductCodeService productCodeService, CSVFileService csvFileService, MessageService messageService) {
        this.productCodeService = productCodeService;
        this.csvFileService = csvFileService;
        this.messageService = messageService;
    }
    @RabbitListener(queues = "Q1")
    public void listenToQueue1(String csvFileContent) {
        var productDtoCodes = csvFileService.processFile(csvFileContent);
        productCodeService.saveAllProductCode(productDtoCodes);
        System.out.println("Received message: " + csvFileContent);
        System.out.println("Received from queue1: " + csvFileContent);
    }

    @RabbitListener(queues = "Q2")
    public void listenToQueue2(String csvFileContent) {
        System.out.println("Received from queue2: " + csvFileContent);
        var productDtoCodes = csvFileService.processFile(csvFileContent);
        for (var productDtoCode : productDtoCodes) {
            messageService.Push_Q3(productDtoCode);
        }
    }

    @RabbitListener(queues = "Q3")
    public void listenToQueue3(String productDtoCodeMessage) {
        System.out.println("Received message: " + productDtoCodeMessage);
        ObjectMapper objectMapper = new ObjectMapper();
        ProductCodeDto productDtoCode = null;
        try {
            productDtoCode = objectMapper.readValue(productDtoCodeMessage, ProductCodeDto.class);
            var productCode=ProductCodeDto.ToEntity(productDtoCode);
            productCodeService.saveProductCode(productCode);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

    }
}