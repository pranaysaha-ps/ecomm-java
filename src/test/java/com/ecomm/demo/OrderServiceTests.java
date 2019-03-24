package com.ecomm.demo;

import com.ecomm.demo.dto.request.OrderRequestDto;
import com.ecomm.demo.model.constants.Constant;
import com.ecomm.demo.service.OrderService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderServiceTests {
    @Autowired
    private OrderService orderService;

    @Test
    public void test_create_order(){
        OrderRequestDto orderRequestDto = new OrderRequestDto();
        orderRequestDto.setProductId("5c97e04739586f1e949f6fb3");
        orderRequestDto.setQuantity(10L);
        orderRequestDto.setUserId("5c97e04739586f1e949f6fb5");
        Map response = orderService.createOrder(orderRequestDto);
        Assert.assertEquals(response.get("message"), Constant.SUCCESS);
    }

    @Test
    public void test_create_order_fail(){
        OrderRequestDto orderRequestDto = new OrderRequestDto();
        orderRequestDto.setProductId("5c97e04739586f1e949f6fb3");
        orderRequestDto.setQuantity(10L);
        orderRequestDto.setUserId("5c97e04739586f1e949f6fb5");
        Map response = orderService.createOrder(orderRequestDto);
        Assert.assertNotNull(response);
    }
}
