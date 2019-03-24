package com.ecomm.demo.controller;

import com.ecomm.demo.dto.request.OrderRequestDto;
import com.ecomm.demo.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/order")
@Slf4j
public class OrderController {
    @Autowired
    private OrderService orderService;

    @RequestMapping(value = "/create", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity createOrder(@Validated OrderRequestDto orderRequestDto){
        return new ResponseEntity(orderService.createOrder(orderRequestDto), HttpStatus.OK);
    }

}
