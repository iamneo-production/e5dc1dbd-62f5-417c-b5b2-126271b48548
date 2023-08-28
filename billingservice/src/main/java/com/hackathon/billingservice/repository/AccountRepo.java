package com.hackathon.billingservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hackathon.billingservice.model.Account;

@Repository
public interface AccountRepo extends JpaRepository<Account, Long> {

}
