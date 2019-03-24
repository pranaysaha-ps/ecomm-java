package com.ecomm.demo.service;

import com.ecomm.demo.dto.ProductDetails;
import com.ecomm.demo.dto.UserDetails;
import com.ecomm.demo.dto.request.OrderRequestDto;
import com.ecomm.demo.model.Inventory;
import com.ecomm.demo.model.Order;
import com.ecomm.demo.model.UserAccount;
import com.ecomm.demo.model.constants.Constant;
import com.ecomm.demo.repository.InventoryRepository;
import com.ecomm.demo.repository.OrderRepository;
import com.ecomm.demo.repository.UserAccountRepository;
import com.ecomm.demo.utils.ResponseUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@Slf4j
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private InventoryService inventoryService;

    @Autowired
    private UserAccountRepository userAccountRepository;

    @Autowired
    private InventoryRepository inventoryRepository;

    public Map createOrder(OrderRequestDto orderRequestDto){
        // Check if the product is available in inventory or not
        long productQuantity = inventoryService.getQuantityByProductId(orderRequestDto.getProductId());
        if(productQuantity==0L){
            return ResponseUtil.getFailedResponseJson(Constant.NO_PRODUCT_INVENTORY, Constant.NO_PRODUCT_INVENTORY);
        }
        if(productQuantity< orderRequestDto.getQuantity()){
            return ResponseUtil.getFailedResponseJson(Constant.LESS_PRODUCT_INVENTORY, Constant.LESS_PRODUCT_INVENTORY);
        }

        Inventory product = inventoryRepository.findByProductId(orderRequestDto.getProductId());
        long previousQuantity = product.getQuantity();
        UserAccount userAccount = userAccountRepository.findById(orderRequestDto.getUserId()).get();
        try {

            Order order = new Order();

            UserDetails userDetails = new UserDetails();
            userDetails.setId(userAccount.getId());
            userDetails.setName(userAccount.getName());
            order.setUserDetails(userDetails);

            // Set necessary product details
            ProductDetails productDetails = new ProductDetails();
            productDetails.setId(product.getId());
            productDetails.setProductName(product.getProductName());
            productDetails.setQuantity(orderRequestDto.getQuantity());
            productDetails.setPrice(orderRequestDto.getQuantity()*product.getPrice());
            order.setProductDetails(productDetails);

            // Update the inventory with the bought items(productQuantity) in order
            product.setQuantity(previousQuantity - productQuantity);
            inventoryRepository.save(product);

            orderRepository.save(order);
            return ResponseUtil.getSuccessResponseJson(Constant.SUCCESS);
        } catch (Exception e) {

            // Rollback
            if(product.getQuantity()< previousQuantity){
                product.setQuantity(previousQuantity+previousQuantity);
                inventoryRepository.save(product);
            }
            return ResponseUtil.getFailedResponseJson(Constant.INTERNAL_SERVER_ERROR, Constant.INTERNAL_SERVER_ERROR);
        }
    }

    //TODO: Add remaining operations out of CRUD operation - Create is implemented

    //TODO: Add scheduler to remove the stale cart items and make those items available in inventory

}
