package com.hackathon.inventoryservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductItems {

    private int productId;
    private String productName;
    private int productQuantity;
}
