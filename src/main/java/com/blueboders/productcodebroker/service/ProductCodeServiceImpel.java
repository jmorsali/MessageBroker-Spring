package com.blueboders.productcodebroker.service;

import com.blueboders.productcodebroker.dtos.ProductCodeDto;
import com.blueboders.productcodebroker.entity.ProductCode;
import com.blueboders.productcodebroker.repository.ProductCodeRepository;
import jakarta.transaction.Transactional;
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
    @Transactional
    public void saveAllProductCode(List<ProductCodeDto> productCodeDtoItems){
        List<ProductCode> productCodeItems = ProductCodeDto.ToEntity(productCodeDtoItems);
        for (var productCode: productCodeItems) {
            if (productCodeRepository.getByCode(productCode.getCode()) == null)
                productCodeRepository.save(productCode);
        }
    }

    @Override
    public void saveProductCode(ProductCode productCode) {
        if (productCodeRepository.getByCode(productCode.getCode()) == null)
            productCodeRepository.save(productCode);
    }
}
