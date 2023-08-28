package com.hackathon.customerservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerRegisterResponse {

    private int customerID;
    private String customerName;
    private String customerEmail;
    private LocalDate customerDateOfBirth;
    private String address;
}
