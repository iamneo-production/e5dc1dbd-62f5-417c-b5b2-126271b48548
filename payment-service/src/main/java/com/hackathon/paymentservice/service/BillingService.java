package com.hackathon.paymentservice.service;

import com.hackathon.paymentservice.entity.Account;
import com.hackathon.paymentservice.model.BillingRequest;
import com.hackathon.paymentservice.model.BillingResponse;

public interface BillingService {
    BillingResponse billingOrder(BillingRequest billingRequest);

    String createAccount(Account account);
}
