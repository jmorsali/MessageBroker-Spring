package com.blueboders.productcodebroker.service;

import com.blueboders.productcodebroker.dtos.PageResult;
import com.blueboders.productcodebroker.dtos.ProductCodeDto;
import com.blueboders.productcodebroker.entity.ProductCode;
import org.springframework.data.domain.PageRequest;
import java.util.List;

public interface ProductCodeService {
    PageResult<ProductCodeDto> getAllProductCode(PageRequest pageable);
    ProductCodeDto getProductCodeByCode(String code);

    void deleteAllProductCode();

    void saveAllProductCode(List<ProductCodeDto> codeDtoItems);

    void saveProductCode(ProductCode productCode);
}
