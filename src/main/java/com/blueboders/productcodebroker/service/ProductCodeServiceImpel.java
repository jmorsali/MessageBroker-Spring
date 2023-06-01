package com.blueboders.productcodebroker.service;

import com.blueboders.productcodebroker.dtos.PageResult;
import com.blueboders.productcodebroker.dtos.ProductCodeDto;
import com.blueboders.productcodebroker.entity.ProductCode;
import com.blueboders.productcodebroker.repository.ProductCodeRepository;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ProductCodeServiceImpel implements  ProductCodeService {

    final ProductCodeRepository productCodeRepository;

    public ProductCodeServiceImpel(ProductCodeRepository productCodeRepository) {
        this.productCodeRepository = productCodeRepository;
    }

    public PageResult<ProductCodeDto> getAllProductCode(PageRequest pageable) {
        Page<ProductCode> productCodeItems = productCodeRepository.findAll(pageable);
        return new PageResult<>(ProductCodeDto.ToDto(productCodeItems.getContent()), productCodeItems.getTotalElements());

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
