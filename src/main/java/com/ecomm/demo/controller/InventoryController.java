package com.ecomm.demo.controller;

import com.ecomm.demo.service.InventoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/inventory/")
@Slf4j

//TODO: Security configuration needs to be added for security as well as cors
public class InventoryController {
    @Autowired
    private InventoryService inventoryService;

    @RequestMapping(value = "getAll", method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity getAllInventory(){
        return new ResponseEntity(inventoryService.findAll(), HttpStatus.OK);
    }
}
