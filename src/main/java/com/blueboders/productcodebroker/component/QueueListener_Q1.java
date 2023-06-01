package com.blueboders.productcodebroker.component;

import com.blueboders.productcodebroker.service.CSVFileService;
import com.blueboders.productcodebroker.service.ProductCodeService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class QueueListener_Q1 {

    private final ProductCodeService productCodeService;
    private final CSVFileService csvFileService;

    @Autowired
    public QueueListener_Q1(ProductCodeService productCodeService, CSVFileService csvFileService) {
        this.productCodeService = productCodeService;
        this.csvFileService = csvFileService;
    }
    @RabbitListener(queues = "Q1")
    public void listenToQueue1(String csvFileContent) {
        System.out.println("Received from queue1: " + csvFileContent);
        var productDtoCodes = csvFileService.processFile(csvFileContent);
        productCodeService.saveAllProductCode(productDtoCodes);
    }

}