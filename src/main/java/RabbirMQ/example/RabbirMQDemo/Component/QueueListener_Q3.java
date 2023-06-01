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
public class QueueListener_Q3 {

    private final ProductCodeService productCodeService;

    @Autowired
    public QueueListener_Q3(ProductCodeService productCodeService) {
        this.productCodeService = productCodeService;
    }

    @RabbitListener(queues = "Q3")
    public void listenToQueue3(String productDtoCodeMessage) {
        System.out.println("Received message Q3: " + productDtoCodeMessage);
        ObjectMapper objectMapper = new ObjectMapper();
        ProductCodeDto productDtoCode = null;
        try {
            productDtoCode = objectMapper.readValue(productDtoCodeMessage, ProductCodeDto.class);
            var productCode = ProductCodeDto.ToEntity(productDtoCode);
            productCodeService.saveProductCode(productCode);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

    }
}