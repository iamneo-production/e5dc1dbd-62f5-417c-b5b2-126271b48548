package com.hackathon.orderservice.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "products")
public class ProductItems {

    @Id
    private int productId;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order orders;

    private String productName;

    private int productQuantity;

    private double productPrice;
    private double totalPrice;
}
