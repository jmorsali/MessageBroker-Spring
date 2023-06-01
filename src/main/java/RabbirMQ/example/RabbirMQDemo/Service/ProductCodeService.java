package RabbirMQ.example.RabbirMQDemo.Service;

import RabbirMQ.example.RabbirMQDemo.DTOs.ProductCodeDto;

import java.util.List;

public interface ProductCodeService {
    List<ProductCodeDto> getAllProductCode();
    ProductCodeDto getProductCodeByCode(String code);

    void deleteAllProductCode();
}
