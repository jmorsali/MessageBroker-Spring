package com.blueboders.productcodebroker.controller;

import com.blueboders.productcodebroker.dtos.ProductCodeDto;
import com.blueboders.productcodebroker.service.CSVFileService;
import com.blueboders.productcodebroker.service.ProductCodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.util.List;


@RestController
@RequestMapping("api/v1/productCode")
public class ProductCodeController {

    private final CSVFileService csvFileService;
    private final ProductCodeService productCodeService;

    @Autowired
    public ProductCodeController(CSVFileService csvFileService, ProductCodeService productCodeService) {
        this.csvFileService = csvFileService;
        this.productCodeService = productCodeService;
    }

    @PostMapping("/upload")
    public ResponseEntity<String> uploadFile(@RequestPart("file") MultipartFile file) throws IOException {

        if (!file.isEmpty()) {
            byte[] bytes = file.getBytes();
            String csvData = new String(bytes);
            try {
                var productDtoCodes = csvFileService.processFile(csvData);
                productCodeService.saveAllProductCode(productDtoCodes);
                return new ResponseEntity<>("File uploaded successfully", HttpStatus.OK);
            } catch (Exception e) {
                return new ResponseEntity<>("UnHandled error during file process ::>" + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);

            }
        } else {
            return new ResponseEntity<>("No file uploaded", HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/all")
    public ResponseEntity<List<ProductCodeDto>> getAllProductCode() {
        try {
            var result = productCodeService.getAllProductCode();
            return new ResponseEntity<>(result, HttpStatus.OK);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/code/{code}")
    public ResponseEntity<ProductCodeDto> getProductCodeByCode(@PathVariable String code) {
        try {
            var result = productCodeService.getProductCodeByCode(code);
            return new ResponseEntity<>(result, HttpStatus.OK);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @DeleteMapping("/delete/all")
    public ResponseEntity<String> deleteAllProductCode() {
        try {
            productCodeService.deleteAllProductCode();
            return new ResponseEntity<>("All product codes deleted successfully", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("UnHandled error during deleting ::>" + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);

        }
    }
}