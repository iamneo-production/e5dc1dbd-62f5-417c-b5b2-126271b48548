package com.hackathon.orderservice.service;

import com.hackathon.orderservice.exception.InvalidProductException;
import com.hackathon.orderservice.model.BillingRequest;
import com.hackathon.orderservice.model.OrderRequest;
import com.hackathon.orderservice.model.OrderResponse;
import com.hackathon.orderservice.model.PurchaseStatus;

public interface OrderService {
    OrderResponse placeOrder(OrderRequest orderRequest) throws InvalidProductException;

    OrderResponse findDetailsByOrderId(BillingRequest billingRequest) throws InvalidProductException;

    OrderResponse updateStatus(PurchaseStatus updatedPurchaseStatus) throws InvalidProductException;
}
