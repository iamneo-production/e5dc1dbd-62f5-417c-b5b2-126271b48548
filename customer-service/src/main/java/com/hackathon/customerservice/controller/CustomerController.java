package com.hackathon.customerservice.controller;

import com.hackathon.customerservice.entity.Customer;
import com.hackathon.customerservice.exception.UserNotFoundException;
import com.hackathon.customerservice.model.*;
import com.hackathon.customerservice.service.CustomerService;
import com.hackathon.customerservice.service.jwt.JwtFilter;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/customer")
public class CustomerController {

    private static final String status="SUCCESS";

    @Autowired
    private CustomerService customerService;



    @Autowired
    private JwtFilter jwtFilter;

    @PostMapping("/register")
    public ResponseEntity<ApiResponse<CustomerRegisterResponse>> registerCustomer
            (@RequestBody @Valid CustomerRegisterRequest customerRegisterRequest){
        CustomerRegisterResponse customerRegister=customerService.register(customerRegisterRequest);
        ApiResponse<CustomerRegisterResponse> response=new ApiResponse<>(status,customerRegister,null);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PostMapping("/sign-in")
    public ResponseEntity<ApiResponse<TokenResponse>> signInCustomer(
            @RequestBody @Valid SignInRequest signInRequest) throws UserNotFoundException {

        Customer customer=customerService.findByUsernamePassword(signInRequest.getCustomerName(),
                signInRequest.getCustomerPassword());

            TokenResponse token=jwtFilter.generateToken(signInRequest.getCustomerName(),customer.getCustomerID());
            ApiResponse<TokenResponse> response=new ApiResponse<>(status,token,null);
            return ResponseEntity.status(HttpStatus.FOUND).body(response);



    }
}
