package com.ecomm.demo.dto.request;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
public class OrderRequestDto {
    @NotBlank
    @NotNull
    public String userId;

    //TODO: Can be updated with list of product IDs as in one order multiple items can be included
    /*
    * In above case the quantity will come with product details as an json object
    * */
    @NotBlank
    @NotNull
    public String productId;

    @Min(value = 1, message = "Please select atleast 1 quantity")
    public long quantity;
}
