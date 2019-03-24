package com.ecomm.demo.model;

import com.ecomm.demo.dto.ProductDetails;
import com.ecomm.demo.dto.UserDetails;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@NoArgsConstructor
@Document(collection = "order")
public class Order {
    @Id
    public String id;

    public UserDetails userDetails;

    // TODO: Make list of products as multiple items can be added in single order
    public ProductDetails productDetails;
}
