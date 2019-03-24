package com.ecomm.demo.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;

@Data
@NoArgsConstructor
public class ProductDetails {
    public String id;
    public String productName;

    @Min(value = 1, message = "Product quantity should be more than or equal to 1")
    public long quantity;

    // Total price
    public Double price;

    //TODO: More details can be added such as description and product properties such as brand, discount etc
}
