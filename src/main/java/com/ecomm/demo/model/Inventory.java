package com.ecomm.demo.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "inventory")
public class Inventory {
    @Id
    public String id;

    public String productName;

    public long quantity;

    // Price of each item
    public Double price;

    //TODO: More details can be added such as description and product properties such as brand, discount etc

    public Inventory(String productName, long quantity, Double price) {
        this.productName = productName;
        this.quantity = quantity;
        this.price = price;
    }
}
