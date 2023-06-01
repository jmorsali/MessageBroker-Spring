package com.blueboders.productcodebroker.service;

import com.blueboders.productcodebroker.dtos.ProductCodeDto;

import java.util.List;

public interface CSVFileService {
    List<ProductCodeDto> processFile(String csvData);
}

