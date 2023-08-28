package com.hackathon.customerservice.repository;

import com.hackathon.customerservice.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CustomerRepository extends JpaRepository<Customer,Integer> {


    Customer findByCustomerName(String customerName);
}
