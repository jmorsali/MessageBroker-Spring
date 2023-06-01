package com.blueboders.productcodebroker.service;

import com.blueboders.productcodebroker.dtos.ProductCodeDto;


public interface MessageService {

    void Push_Q1(String message);

    void Push_Q2(byte[] message);

    void Push_Q3(ProductCodeDto productCodeDto);

}
