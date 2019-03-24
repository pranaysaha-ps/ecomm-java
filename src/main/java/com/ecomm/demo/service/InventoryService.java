package com.ecomm.demo.service;

import com.ecomm.demo.model.Inventory;
import com.ecomm.demo.model.constants.Constant;
import com.ecomm.demo.repository.InventoryRepository;
import com.ecomm.demo.utils.ResponseUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@Slf4j
public class InventoryService {
    @Autowired
    private InventoryRepository inventoryRepository;

    public Map findAll(){
        List<Inventory> inventoryList = inventoryRepository.findAll();
        return ResponseUtil.getSuccessResponseJsonWithData(inventoryList, Constant.SUCCESS);
    }

    public long getQuantityByProductId(String productId){
        Inventory inventory = inventoryRepository.findByProductId(productId);
        return inventory.quantity;
    }

    //TODO: Add remaining operations out of CRUD operation - Create is implemented
}
