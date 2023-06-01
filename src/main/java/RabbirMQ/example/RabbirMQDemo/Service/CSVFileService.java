package RabbirMQ.example.RabbirMQDemo.Service;

import RabbirMQ.example.RabbirMQDemo.DTOs.ProductCodeDto;

import java.util.List;

public interface CSVFileService {
    List<ProductCodeDto> processFile(String csvData);
}

