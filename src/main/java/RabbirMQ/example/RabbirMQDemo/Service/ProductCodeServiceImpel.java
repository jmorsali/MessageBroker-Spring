package RabbirMQ.example.RabbirMQDemo.Service;

import RabbirMQ.example.RabbirMQDemo.DTOs.ProductCodeDto;
import RabbirMQ.example.RabbirMQDemo.Entity.ProductCode;
import RabbirMQ.example.RabbirMQDemo.Repository.ProductCodeRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductCodeServiceImpel implements  ProductCodeService {

    final ProductCodeRepository productCodeRepository;

    public ProductCodeServiceImpel(ProductCodeRepository productCodeRepository) {
        this.productCodeRepository = productCodeRepository;
    }

    public List<ProductCodeDto> getAllProductCode() {
        var productCodeItems = productCodeRepository.findAll().stream().toList();
        return ProductCodeDto.ToDto(productCodeItems);
    }

    public ProductCodeDto getProductCodeByCode(String code) {
        var productCodeItem = productCodeRepository.getByCode(code);
        return ProductCodeDto.ToDto(productCodeItem);
    }

    @Override
    public void deleteAllProductCode() {
        productCodeRepository.deleteAll();
    }

    @Override
    public void saveAllProductCode(List<ProductCodeDto> codeDtoItems){
        List<ProductCode> codesItem = ProductCodeDto.ToEntity(codeDtoItems);
        productCodeRepository.saveAll(codesItem);
    }

    @Override
    public void saveProductCode(ProductCode productCode) {
        productCodeRepository.save(productCode);
    }
}
