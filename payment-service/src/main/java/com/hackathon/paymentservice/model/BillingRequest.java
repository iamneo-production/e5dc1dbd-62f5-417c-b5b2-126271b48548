package com.hackathon.paymentservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BillingRequest {

    private String orderId;
    private long accountNumber;
    private String accountHolderName;
    private String secretPin;
}
