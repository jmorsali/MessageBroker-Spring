package com.blueboders.productcodebroker.service;

import com.blueboders.productcodebroker.dtos.ProductCodeDto;
import com.blueboders.productcodebroker.entity.ProductCode;

import java.util.List;

public interface ProductCodeService {
    List<ProductCodeDto> getAllProductCode();
    ProductCodeDto getProductCodeByCode(String code);

    void deleteAllProductCode();

    void saveAllProductCode(List<ProductCodeDto> codeDtoItems);

    void saveProductCode(ProductCode productCode);
}
