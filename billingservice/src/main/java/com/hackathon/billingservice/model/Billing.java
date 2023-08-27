package com.hackathon.billingservice.model;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Billing {
    
    @Id
	@GeneratedValue(generator = "billSeq", strategy = GenerationType.IDENTITY)
	@SequenceGenerator(name = "billSeq", initialValue = 1001, allocationSize = 1)
	@Column(name = "billingid")
	private int billingId;
	
	@Column(name = "orderid")
	private int orderId;
	
	@Column(name = "purchasedate")
	private LocalDate purcahseDate;
	
	@Column(name = "purchaseTime")
	private String purcahseTime;
	
	@Column(name = "accountnumber")
	private long accountNumber;
	
	@Column(name = "productid")
	private int productId;
	
	@Column(name = "purchaseamount")
	private float purchaseAmount;

}
