package com.hackathon.paymentservice.controller;


import com.hackathon.paymentservice.entity.Account;
import com.hackathon.paymentservice.model.ApiResponse;
import com.hackathon.paymentservice.model.BillingRequest;
import com.hackathon.paymentservice.model.BillingResponse;
import com.hackathon.paymentservice.model.ErrorMessage;
import com.hackathon.paymentservice.service.BillingService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/billing")
public class BillingController {
    private static final String STATUS="SUCCESS";

    @Autowired
    private BillingService billingService;



    @PostMapping("/create-account")
    public ResponseEntity<ApiResponse<String>> createAccount(@RequestBody Account account){
        System.out.println("Create Account COntroller");
        String accountCreation=billingService.createAccount(account);
        ApiResponse<String> response=new ApiResponse<>(STATUS,accountCreation,null);

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PostMapping("/payment")
    @CircuitBreaker(name = "fall-back", fallbackMethod = "fallbackMethod")
    public ResponseEntity<ApiResponse<BillingResponse>> paymentProcessing
            (@RequestBody BillingRequest billingRequest){

        BillingResponse billingResponse=billingService.billingOrder(billingRequest);
        ApiResponse<BillingResponse> response=new ApiResponse<>(STATUS,billingResponse,null);

        return ResponseEntity.status(HttpStatus.OK).body(response);

    }

    public ResponseEntity<ApiResponse<String>> fallbackMethod(BillingRequest billingRequest, RuntimeException runtimeException){

        ErrorMessage errorMessage=new ErrorMessage("1001","Unavailable Service");
        ApiResponse<String> response=new ApiResponse<>("Error",null, errorMessage);
        return ResponseEntity.status(org.springframework.http.HttpStatus.GATEWAY_TIMEOUT).body(response);

    }
}
