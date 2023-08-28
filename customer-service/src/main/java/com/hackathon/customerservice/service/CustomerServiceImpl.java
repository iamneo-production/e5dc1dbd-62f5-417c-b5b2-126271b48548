package com.hackathon.customerservice.service;


import com.hackathon.customerservice.entity.Customer;
import com.hackathon.customerservice.exception.UserNotFoundException;
import com.hackathon.customerservice.model.CustomerRegisterRequest;
import com.hackathon.customerservice.model.CustomerRegisterResponse;
import com.hackathon.customerservice.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;
    @Override
    public CustomerRegisterResponse register(CustomerRegisterRequest customerRegisterRequest) {

        Customer customer=new Customer();
        customer.setCustomerName(customerRegisterRequest.getCustomerName());
        customer.setCustomerEmail(customerRegisterRequest.getCustomerEmail());
        customer.setCustomerDateOfBirth(customerRegisterRequest.getCustomerDateOfBirth());
        customer.setAddress(customerRegisterRequest.getAddress());
        customer.setPassword(passwordEncoder.encode(customerRegisterRequest.getPassword()));

        Customer customerResponse=customerRepository.save(customer);

        CustomerRegisterResponse customerRegisterResponse=new CustomerRegisterResponse
                (customerResponse.getCustomerID(), customerResponse.getCustomerName(),
                        customer.getCustomerEmail(), customerResponse.getCustomerDateOfBirth(),
                        customerResponse.getAddress());
        return customerRegisterResponse;
    }

    @Override
    public Customer findByUsernamePassword(String customerName, String customerPassword) throws UserNotFoundException {
        Customer customer = customerRepository.findByCustomerName(customerName);

        if (customer == null || !passwordEncoder.matches(customerPassword, customer.getPassword())) {
            throw new UserNotFoundException("User Not Found");
        }

        return customer;
    }

}
