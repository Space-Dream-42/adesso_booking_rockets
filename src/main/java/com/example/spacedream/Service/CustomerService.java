package com.example.spacedream.Service;

import com.example.spacedream.Entities.Customer;
import com.example.spacedream.Repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.Optional;


@Service
public class CustomerService {

    private CustomerRepository customerRepository;

    @Autowired
    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }


    public void createCustomer(Customer customer) {
        customerRepository.save(customer);
    }


    public void updateCustomer(Customer customer) throws EntityNotFoundException {
        if (customerRepository.existsById(customer.getUserName())) {
            customerRepository.save(customer);
        } else {
            throw new EntityNotFoundException("This instance doesn't exits!");
        }
    }


    public Optional<Customer> getCustomer(String userName) {
        return customerRepository.findById(userName);
    }


    public Boolean validCustomerAndPassword(String userName, String password) {
        Optional<Customer> customer = this.getCustomer(userName);
        return customer.isPresent() && customer.get().getPassword().equals(password);
    }
}
