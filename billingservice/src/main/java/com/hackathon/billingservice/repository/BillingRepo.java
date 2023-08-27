package com.hackathon.billingservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hackathon.billingservice.model.Billing;

public interface BillingRepo extends JpaRepository<Billing, Integer> {

}

