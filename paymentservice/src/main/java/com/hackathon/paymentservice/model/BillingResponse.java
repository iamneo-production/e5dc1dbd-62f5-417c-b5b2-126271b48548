package com.hackathon.paymentservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BillingResponse {

    private int billingId;
    private Order orderId;
    private LocalDate billingDate;
    private String billingTime;
    private long accountNumber;

    private String purchaseStatus;
}
