package com.blueboders.productcodebroker.component;

import com.blueboders.productcodebroker.service.CSVFileService;
import com.blueboders.productcodebroker.service.MessageService;
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