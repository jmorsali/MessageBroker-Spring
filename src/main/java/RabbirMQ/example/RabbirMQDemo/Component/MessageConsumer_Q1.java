package RabbirMQ.example.RabbirMQDemo.Component;

import RabbirMQ.example.RabbirMQDemo.Repository.ProductCodeRepository;
import RabbirMQ.example.RabbirMQDemo.Service.CSVFileService;
import RabbirMQ.example.RabbirMQDemo.Service.ProductCodeService;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MessageConsumer_Q1 implements MessageListener {

    private final ProductCodeService productCodeService;
    private final CSVFileService csvFileService;

    @Autowired
    public MessageConsumer_Q1(ProductCodeService productCodeService, CSVFileService csvFileService) {
        this.productCodeService = productCodeService;
        this.csvFileService = csvFileService;
    }

    @Override
    public void onMessage(Message message) {
        String csvFileContent = new String(message.getBody());
        var productDtoCodes = csvFileService.processFile(csvFileContent);
        productCodeService.saveAllProductCode(productDtoCodes);
        System.out.println("Received message: " + csvFileContent);
    }
}
