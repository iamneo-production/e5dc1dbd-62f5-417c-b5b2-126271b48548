package com.hackathon.orderservice.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "productList")
public class Product {

    @Id
    private int productId;
    private String productName;
    private String productDescription;
    private double productPrice;
}
