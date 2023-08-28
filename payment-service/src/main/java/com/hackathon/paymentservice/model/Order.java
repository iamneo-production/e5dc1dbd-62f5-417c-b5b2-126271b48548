package com.hackathon.paymentservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Order {

    private String orderId;
    private List<Products> productItemsList;
    private int customerId;
    private String customerName;
    private LocalDate purchaseDate;
    private double purchaseAmount;
    private String purchaseStatus;
}
