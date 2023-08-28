package com.hackathon.paymentservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Products {
    private int productId;
    private String productName;
    private int productQuantity;

    private double productPrice;

}
