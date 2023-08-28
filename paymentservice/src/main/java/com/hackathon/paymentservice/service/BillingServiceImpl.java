package com.hackathon.paymentservice.service;

import com.hackathon.paymentservice.entity.Account;
import com.hackathon.paymentservice.entity.Billing;
import com.hackathon.paymentservice.exception.InvalidAccountException;
import com.hackathon.paymentservice.feign.OrderService;
import com.hackathon.paymentservice.model.*;
import com.hackathon.paymentservice.repository.AccountRepository;
import com.hackathon.paymentservice.repository.BillingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.QueryAnnotation;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Service
public class BillingServiceImpl implements BillingService{

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private OrderService orderService;

    @Autowired
    private BillingRepository billingRepository;
    @Autowired
    private AccountRepository accountRepository;
    @Override
    public BillingResponse billingOrder(BillingRequest billingRequest) {
        Billing billingDetails;
        Order orderUpdatedDetails;
        String purchaseStatus="Purchase Successful";

        OrderRequest orderRequest=new OrderRequest(billingRequest.getOrderId());

        Order orderDetails=orderService.findDetailsByOrderId(orderRequest).getBody().getData();

        Account account=accountRepository.findById(billingRequest.getAccountNumber()).orElseThrow(() -> new InvalidAccountException("Invalid Account Details"));

        if (account == null || !passwordEncoder.matches(billingRequest.getSecretPin(), account.getSecretPin())) {
            throw new InvalidAccountException("Invalid Secret Pin Details");
        } else if (orderDetails==null) {
            throw new InvalidAccountException("Invalid Order Details");
        } else if (account.getAccountNumber()<orderDetails.getPurchaseAmount()) {
            throw new InvalidAccountException("Purchase Amount is greater than Account Balance");

        } else{

            Billing billing=new Billing();
            billing.setOrderId(orderDetails.getOrderId());
            billing.setBillingDate(LocalDate.now());
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
            String formattedTime = LocalDate.now().format(formatter);
            billing.setBillingTime(formattedTime);
            billing.setPurchaseAmount(orderDetails.getPurchaseAmount());
            billing.setAccountNumber(account.getAccountNumber());

            billingDetails=billingRepository.save(billing);

            orderDetails.setPurchaseStatus(purchaseStatus);

            PurchaseStatus updatedPurchaseStatus=new PurchaseStatus(purchaseStatus);

            orderUpdatedDetails=orderService.updatePurchaseStatus(updatedPurchaseStatus).getBody().getData();
        }

        return new BillingResponse(billingDetails.getBillingId(),orderUpdatedDetails,billingDetails.getBillingDate(),
                billingDetails.getBillingTime(),billingDetails.getAccountNumber(),purchaseStatus);
    }

    @Override
    public String createAccount(Account account) {
        Account accountValue=new Account();

        accountValue.setAccountNumber(account.getAccountNumber());
        accountValue.setAccountHolderName(account.getAccountHolderName());
        accountValue.setAccountBalance(0.0);
        accountValue.setSecretPin(passwordEncoder.encode(account.getSecretPin()));
        accountRepository.save(accountValue);
        return "Created";
    }
}
