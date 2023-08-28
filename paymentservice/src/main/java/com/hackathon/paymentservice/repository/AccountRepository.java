package com.hackathon.paymentservice.repository;

import com.hackathon.paymentservice.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {
}

