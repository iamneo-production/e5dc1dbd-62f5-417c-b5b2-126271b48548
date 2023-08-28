package com.hackathon.customerservice.service;

import com.hackathon.customerservice.entity.Customer;
import com.hackathon.customerservice.exception.UserNotFoundException;
import com.hackathon.customerservice.model.CustomerRegisterRequest;
import com.hackathon.customerservice.model.CustomerRegisterResponse;
import com.hackathon.customerservice.model.SignInRequest;

import java.util.Optional;

public interface CustomerService {
    CustomerRegisterResponse register(CustomerRegisterRequest customerRegisterRequest);


    Customer findByUsernamePassword(String customerName, String customerPassword) throws UserNotFoundException;
}
