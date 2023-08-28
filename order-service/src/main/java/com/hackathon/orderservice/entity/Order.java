package com.hackathon.orderservice.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "orders")
public class Order {
    @Id
    private String orderId;
    private int customerId;
    private String customerName;
    private LocalDate purchaseDate;
    private double purchaseAmount;
    private String purchaseStatus;
    @OneToMany(mappedBy = "orders", cascade = CascadeType.ALL)
    private List<ProductItems> productItemsList;
}
