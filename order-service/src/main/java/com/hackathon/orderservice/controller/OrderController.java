package com.hackathon.orderservice.controller;

import com.hackathon.orderservice.exception.InvalidProductException;
import com.hackathon.orderservice.model.*;
import com.hackathon.orderservice.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/order")
public class OrderController {

    private static final String STATUS="SUCCESS";
    @Autowired
    private OrderService orderService;

    @GetMapping("/order-details")
    ResponseEntity<ApiResponse<OrderResponse>> findDetailsByOrderId(@RequestBody BillingRequest billingRequest) throws InvalidProductException {
        OrderResponse orderResponse=orderService.findDetailsByOrderId(billingRequest);

        ApiResponse<OrderResponse> response=new ApiResponse<>(STATUS,orderResponse,null);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }


    @PostMapping("/order-place")
    public ResponseEntity<ApiResponse<OrderResponse>> placeOrder(@RequestBody OrderRequest orderRequest) throws InvalidProductException {

        OrderResponse orderResponse=orderService.placeOrder(orderRequest);
        ApiResponse<OrderResponse> response=new ApiResponse<>(STATUS,orderResponse,null);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }


    @PutMapping(value = "/order/update-status")
    ResponseEntity<ApiResponse<OrderResponse>> updatePurchaseStatus(@RequestBody PurchaseStatus updatedPurchaseStatus){

        OrderResponse orderResponse =orderService.updateStatus(updatedPurchaseStatus);
        ApiResponse<OrderResponse> response=new ApiResponse<>(STATUS,orderResponse,null);
        return ResponseEntity.status(HttpStatus.OK).body(response);

    }




}
