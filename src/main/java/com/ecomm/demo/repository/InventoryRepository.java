package com.ecomm.demo.repository;

import com.ecomm.demo.model.Inventory;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface InventoryRepository extends MongoRepository<Inventory, String> {

    @Query(value = "{id:?0}")
    Inventory findByProductId(String productId);
}
