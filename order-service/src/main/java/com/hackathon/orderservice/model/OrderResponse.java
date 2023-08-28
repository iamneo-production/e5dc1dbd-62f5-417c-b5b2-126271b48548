package com.hackathon.orderservice.model;

import com.hackathon.orderservice.entity.ProductItems;
import jakarta.persistence.CascadeType;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderResponse {
    private String orderId;
    private List<Products> productItemsList;
    private int customerId;
    private String customerName;
    private LocalDate purchaseDate;
    private double purchaseAmount;
    private String purchaseStatus;
}
