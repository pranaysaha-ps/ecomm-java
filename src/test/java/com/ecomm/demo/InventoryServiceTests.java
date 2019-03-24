package com.ecomm.demo;

import com.ecomm.demo.service.InventoryService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class InventoryServiceTests {
    @Autowired
    private InventoryService inventoryService;

    @Test
    public void test_get_product_count() {
        long qunatity = inventoryService.getQuantityByProductId("5c97e04739586f1e949f6fb3");
        System.out.println(qunatity);
        Assert.assertNotEquals(0L, qunatity);
    }
}
