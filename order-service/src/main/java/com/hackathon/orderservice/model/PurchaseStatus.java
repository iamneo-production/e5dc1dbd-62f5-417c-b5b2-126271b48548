package com.hackathon.orderservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PurchaseStatus {

    private String purchaseStatus;
    private String orderId;
 }
