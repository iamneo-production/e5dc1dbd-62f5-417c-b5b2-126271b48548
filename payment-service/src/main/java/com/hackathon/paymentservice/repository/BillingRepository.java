package com.hackathon.paymentservice.repository;

import com.hackathon.paymentservice.entity.Billing;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BillingRepository extends JpaRepository<Billing, Integer> {
}
