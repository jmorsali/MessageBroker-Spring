package RabbirMQ.example.RabbirMQDemo.Service;

import RabbirMQ.example.RabbirMQDemo.DTOs.ProductCodeDto;
import RabbirMQ.example.RabbirMQDemo.Entity.ProductCode;

import java.util.List;

public interface ProductCodeService {
    List<ProductCodeDto> getAllProductCode();
    ProductCodeDto getProductCodeByCode(String code);

    void deleteAllProductCode();

    void saveAllProductCode(List<ProductCodeDto> codeDtoItems);

    void saveProductCode(ProductCode productCode);
}
