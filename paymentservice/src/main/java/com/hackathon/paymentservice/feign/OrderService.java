package com.hackathon.paymentservice.feign;

import com.hackathon.paymentservice.model.ApiResponse;
import com.hackathon.paymentservice.model.Order;
import com.hackathon.paymentservice.model.OrderRequest;
import com.hackathon.paymentservice.model.PurchaseStatus;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "ORDER-SERVICE")
public interface OrderService {

    @GetMapping(value = "/order/order-details")
    ResponseEntity<ApiResponse<Order>> findDetailsByOrderId(@RequestBody OrderRequest orderRequest);

    @PutMapping(value = "/order/update-status")
    ResponseEntity<ApiResponse<Order>> updatePurchaseStatus(@RequestBody PurchaseStatus updatedPurchaseStatus);
}
