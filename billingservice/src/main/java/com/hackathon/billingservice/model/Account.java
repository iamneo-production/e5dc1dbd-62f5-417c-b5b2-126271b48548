package com.hackathon.billingservice.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Account {
	
	@Id
	@Column(name = "accountnumber")
	private long accountNumber;
	
	@Column(name = "accountholdername")
	private String accountHolderName;
	
	@Column(name = "accountbalance")
	private float accountBalance;
	

}